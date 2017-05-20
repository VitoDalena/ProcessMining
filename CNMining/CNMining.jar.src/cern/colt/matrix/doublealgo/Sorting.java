/*     */ package cern.colt.matrix.doublealgo;
/*     */ 
/*     */ import cern.colt.GenericSorting;
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Swapper;
/*     */ import cern.colt.Timer;
/*     */ import cern.colt.function.DoubleComparator;
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.matrix.DoubleFactory2D;
/*     */ import cern.colt.matrix.DoubleFactory3D;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.DoubleMatrix3D;
/*     */ import cern.colt.matrix.impl.AbstractFormatter;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix3D;
/*     */ import cern.colt.matrix.impl.DenseDoubleMatrix1D;
/*     */ import cern.jet.math.Functions;
/*     */ import cern.jet.random.engine.DRand;
/*     */ import hep.aida.bin.BinFunction1D;
/*     */ import hep.aida.bin.BinFunctions1D;
/*     */ import hep.aida.bin.QuantileBin1D;
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
/*     */ public class Sorting
/*     */   extends PersistentObject
/*     */ {
/*  42 */   public static final Sorting quickSort = new Sorting();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  47 */   public static final Sorting mergeSort = new Sorting() {
/*     */     protected void runSort(int[] paramAnonymousArrayOfInt, int paramAnonymousInt1, int paramAnonymousInt2, IntComparator paramAnonymousIntComparator) {
/*  49 */       cern.colt.Sorting.mergeSort(paramAnonymousArrayOfInt, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousIntComparator);
/*     */     }
/*     */     
/*  52 */     protected void runSort(int paramAnonymousInt1, int paramAnonymousInt2, IntComparator paramAnonymousIntComparator, Swapper paramAnonymousSwapper) { GenericSorting.mergeSort(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousIntComparator, paramAnonymousSwapper); }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int compareNaN(double paramDouble1, double paramDouble2)
/*     */   {
/*  63 */     if (paramDouble1 != paramDouble1) {
/*  64 */       if (paramDouble2 != paramDouble2) return 0;
/*  65 */       return 1;
/*     */     }
/*  67 */     return -1;
/*     */   }
/*     */   
/*  70 */   protected void runSort(int[] paramArrayOfInt, int paramInt1, int paramInt2, IntComparator paramIntComparator) { cern.colt.Sorting.quickSort(paramArrayOfInt, paramInt1, paramInt2, paramIntComparator); }
/*     */   
/*     */   protected void runSort(int paramInt1, int paramInt2, IntComparator paramIntComparator, Swapper paramSwapper) {
/*  73 */     GenericSorting.quickSort(paramInt1, paramInt2, paramIntComparator, paramSwapper);
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
/*     */   public DoubleMatrix1D sort(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/*  98 */     int[] arrayOfInt = new int[paramDoubleMatrix1D.size()];
/*  99 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/* 101 */     IntComparator local2 = new IntComparator() { private final DoubleMatrix1D val$vector;
/*     */       
/* 103 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { double d1 = this.val$vector.getQuick(paramAnonymousInt1);
/* 104 */         double d2 = this.val$vector.getQuick(paramAnonymousInt2);
/* 105 */         if ((d1 != d1) || (d2 != d2)) return Sorting.compareNaN(d1, d2);
/* 106 */         return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
/*     */       }
/*     */       
/* 109 */     };
/* 110 */     runSort(arrayOfInt, 0, arrayOfInt.length, local2);
/*     */     
/* 112 */     return paramDoubleMatrix1D.viewSelection(arrayOfInt);
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
/*     */   public DoubleMatrix1D sort(DoubleMatrix1D paramDoubleMatrix1D, DoubleComparator paramDoubleComparator)
/*     */   {
/* 138 */     int[] arrayOfInt = new int[paramDoubleMatrix1D.size()];
/* 139 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/* 141 */     IntComparator local3 = new IntComparator() { private final DoubleComparator val$c;
/*     */       
/* 143 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$c.compare(this.val$vector.getQuick(paramAnonymousInt1), this.val$vector.getQuick(paramAnonymousInt2));
/*     */       }
/*     */ 
/* 146 */     };
/* 147 */     runSort(arrayOfInt, 0, arrayOfInt.length, local3);
/*     */     
/* 149 */     return paramDoubleMatrix1D.viewSelection(arrayOfInt);
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
/*     */   private final DoubleMatrix1D val$vector;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D sort(DoubleMatrix2D paramDoubleMatrix2D, double[] paramArrayOfDouble)
/*     */   {
/* 225 */     int i = paramDoubleMatrix2D.rows();
/* 226 */     if (paramArrayOfDouble.length != i) { throw new IndexOutOfBoundsException("aggregates.length != matrix.rows()");
/*     */     }
/*     */     
/* 229 */     int[] arrayOfInt = new int[i];
/* 230 */     int j = i; do { arrayOfInt[j] = j;j--; } while (j >= 0);
/*     */     
/*     */ 
/* 233 */     IntComparator local4 = new IntComparator() { private final double[] val$aggregates;
/*     */       
/* 235 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { double d1 = this.val$aggregates[paramAnonymousInt1];
/* 236 */         double d2 = this.val$aggregates[paramAnonymousInt2];
/* 237 */         if ((d1 != d1) || (d2 != d2)) return Sorting.compareNaN(d1, d2);
/* 238 */         return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
/*     */       }
/*     */       
/* 241 */     };
/* 242 */     Swapper local5 = new Swapper() { private final int[] val$indexes;
/*     */       private final double[] val$aggregates;
/*     */       
/* 245 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { int i = this.val$indexes[paramAnonymousInt1];this.val$indexes[paramAnonymousInt1] = this.val$indexes[paramAnonymousInt2];this.val$indexes[paramAnonymousInt2] = i;
/* 246 */         double d = this.val$aggregates[paramAnonymousInt1];this.val$aggregates[paramAnonymousInt1] = this.val$aggregates[paramAnonymousInt2];this.val$aggregates[paramAnonymousInt2] = d;
/*     */       }
/*     */       
/*     */ 
/* 250 */     };
/* 251 */     runSort(0, i, local4, local5);
/*     */     
/*     */ 
/*     */ 
/* 255 */     return paramDoubleMatrix2D.viewSelection(arrayOfInt, null);
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
/*     */   public DoubleMatrix2D sort(DoubleMatrix2D paramDoubleMatrix2D, int paramInt)
/*     */   {
/* 296 */     if ((paramInt < 0) || (paramInt >= paramDoubleMatrix2D.columns())) { throw new IndexOutOfBoundsException("column=" + paramInt + ", matrix=" + AbstractFormatter.shape(paramDoubleMatrix2D));
/*     */     }
/* 298 */     int[] arrayOfInt = new int[paramDoubleMatrix2D.rows()];
/* 299 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/* 301 */     DoubleMatrix1D localDoubleMatrix1D = paramDoubleMatrix2D.viewColumn(paramInt);
/* 302 */     IntComparator local6 = new IntComparator() { private final DoubleMatrix1D val$col;
/*     */       
/* 304 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { double d1 = this.val$col.getQuick(paramAnonymousInt1);
/* 305 */         double d2 = this.val$col.getQuick(paramAnonymousInt2);
/* 306 */         if ((d1 != d1) || (d2 != d2)) return Sorting.compareNaN(d1, d2);
/* 307 */         return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
/*     */       }
/*     */       
/* 310 */     };
/* 311 */     runSort(arrayOfInt, 0, arrayOfInt.length, local6);
/*     */     
/*     */ 
/*     */ 
/* 315 */     return paramDoubleMatrix2D.viewSelection(arrayOfInt, null);
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
/*     */   public DoubleMatrix2D sort(DoubleMatrix2D paramDoubleMatrix2D, DoubleMatrix1DComparator paramDoubleMatrix1DComparator)
/*     */   {
/* 341 */     int[] arrayOfInt = new int[paramDoubleMatrix2D.rows()];
/* 342 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/* 344 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D = new DoubleMatrix1D[paramDoubleMatrix2D.rows()];
/* 345 */     int j = arrayOfDoubleMatrix1D.length; do { arrayOfDoubleMatrix1D[j] = paramDoubleMatrix2D.viewRow(j);j--; } while (j >= 0);
/*     */     
/* 347 */     IntComparator local7 = new IntComparator() { private final DoubleMatrix1DComparator val$c;
/*     */       private final DoubleMatrix1D[] val$views;
/*     */       
/* 350 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$c.compare(this.val$views[paramAnonymousInt1], this.val$views[paramAnonymousInt2]);
/*     */       }
/*     */ 
/* 353 */     };
/* 354 */     runSort(arrayOfInt, 0, arrayOfInt.length, local7);
/*     */     
/*     */ 
/*     */ 
/* 358 */     return paramDoubleMatrix2D.viewSelection(arrayOfInt, null);
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
/*     */   public DoubleMatrix2D sort(DoubleMatrix2D paramDoubleMatrix2D, BinFunction1D paramBinFunction1D)
/*     */   {
/* 432 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix2D.like(1, paramDoubleMatrix2D.rows());
/* 433 */     BinFunction1D[] arrayOfBinFunction1D = { paramBinFunction1D };
/* 434 */     Statistic.aggregate(paramDoubleMatrix2D.viewDice(), arrayOfBinFunction1D, localDoubleMatrix2D);
/* 435 */     double[] arrayOfDouble = localDoubleMatrix2D.viewRow(0).toArray();
/* 436 */     return sort(paramDoubleMatrix2D, arrayOfDouble);
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
/*     */   public DoubleMatrix3D sort(DoubleMatrix3D paramDoubleMatrix3D, int paramInt1, int paramInt2)
/*     */   {
/* 460 */     if ((paramInt1 < 0) || (paramInt1 >= paramDoubleMatrix3D.rows())) throw new IndexOutOfBoundsException("row=" + paramInt1 + ", matrix=" + AbstractFormatter.shape(paramDoubleMatrix3D));
/* 461 */     if ((paramInt2 < 0) || (paramInt2 >= paramDoubleMatrix3D.columns())) { throw new IndexOutOfBoundsException("column=" + paramInt2 + ", matrix=" + AbstractFormatter.shape(paramDoubleMatrix3D));
/*     */     }
/* 463 */     int[] arrayOfInt = new int[paramDoubleMatrix3D.slices()];
/* 464 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/* 466 */     DoubleMatrix1D localDoubleMatrix1D = paramDoubleMatrix3D.viewRow(paramInt1).viewColumn(paramInt2);
/* 467 */     IntComparator local8 = new IntComparator() { private final DoubleMatrix1D val$sliceView;
/*     */       
/* 469 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { double d1 = this.val$sliceView.getQuick(paramAnonymousInt1);
/* 470 */         double d2 = this.val$sliceView.getQuick(paramAnonymousInt2);
/* 471 */         if ((d1 != d1) || (d2 != d2)) return Sorting.compareNaN(d1, d2);
/* 472 */         return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
/*     */       }
/*     */       
/* 475 */     };
/* 476 */     runSort(arrayOfInt, 0, arrayOfInt.length, local8);
/*     */     
/*     */ 
/*     */ 
/* 480 */     return paramDoubleMatrix3D.viewSelection(arrayOfInt, null, null);
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
/*     */   public DoubleMatrix3D sort(DoubleMatrix3D paramDoubleMatrix3D, DoubleMatrix2DComparator paramDoubleMatrix2DComparator)
/*     */   {
/* 506 */     int[] arrayOfInt = new int[paramDoubleMatrix3D.slices()];
/* 507 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/* 509 */     DoubleMatrix2D[] arrayOfDoubleMatrix2D = new DoubleMatrix2D[paramDoubleMatrix3D.slices()];
/* 510 */     int j = arrayOfDoubleMatrix2D.length; do { arrayOfDoubleMatrix2D[j] = paramDoubleMatrix3D.viewSlice(j);j--; } while (j >= 0);
/*     */     
/* 512 */     IntComparator local9 = new IntComparator() { private final DoubleMatrix2DComparator val$c;
/*     */       private final DoubleMatrix2D[] val$views;
/*     */       
/* 515 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$c.compare(this.val$views[paramAnonymousInt1], this.val$views[paramAnonymousInt2]);
/*     */       }
/*     */ 
/* 518 */     };
/* 519 */     runSort(arrayOfInt, 0, arrayOfInt.length, local9);
/*     */     
/*     */ 
/*     */ 
/* 523 */     return paramDoubleMatrix3D.viewSelection(arrayOfInt, null, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void zdemo1()
/*     */   {
/* 530 */     Sorting localSorting = quickSort;
/* 531 */     DoubleMatrix2D localDoubleMatrix2D = DoubleFactory2D.dense.descending(4, 3);
/* 532 */     DoubleMatrix1DComparator local10 = new DoubleMatrix1DComparator() {
/*     */       public int compare(DoubleMatrix1D paramAnonymousDoubleMatrix1D1, DoubleMatrix1D paramAnonymousDoubleMatrix1D2) {
/* 534 */         double d1 = paramAnonymousDoubleMatrix1D1.zSum();double d2 = paramAnonymousDoubleMatrix1D2.zSum();
/* 535 */         return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
/*     */       }
/* 537 */     };
/* 538 */     System.out.println("unsorted:" + localDoubleMatrix2D);
/* 539 */     System.out.println("sorted  :" + localSorting.sort(localDoubleMatrix2D, local10));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void zdemo2()
/*     */   {
/* 546 */     Sorting localSorting = quickSort;
/* 547 */     DoubleMatrix3D localDoubleMatrix3D = DoubleFactory3D.dense.descending(4, 3, 2);
/* 548 */     DoubleMatrix2DComparator local11 = new DoubleMatrix2DComparator() {
/*     */       public int compare(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) {
/* 550 */         double d1 = paramAnonymousDoubleMatrix2D1.zSum();
/* 551 */         double d2 = paramAnonymousDoubleMatrix2D2.zSum();
/* 552 */         return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
/*     */       }
/* 554 */     };
/* 555 */     System.out.println("unsorted:" + localDoubleMatrix3D);
/* 556 */     System.out.println("sorted  :" + localSorting.sort(localDoubleMatrix3D, local11));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void zdemo3()
/*     */   {
/* 563 */     Sorting localSorting = quickSort;
/* 564 */     double[] arrayOfDouble = { 0.5D, 1.5D, 2.5D, 3.5D };
/* 565 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D = new DenseDoubleMatrix1D(arrayOfDouble);
/* 566 */     DoubleComparator local12 = new DoubleComparator() {
/*     */       public int compare(double paramAnonymousDouble1, double paramAnonymousDouble2) {
/* 568 */         double d1 = Math.sin(paramAnonymousDouble1);double d2 = Math.sin(paramAnonymousDouble2);
/* 569 */         return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
/*     */       }
/* 571 */     };
/* 572 */     System.out.println("unsorted:" + localDenseDoubleMatrix1D);
/*     */     
/* 574 */     DoubleMatrix1D localDoubleMatrix1D = localSorting.sort(localDenseDoubleMatrix1D, local12);
/* 575 */     System.out.println("sorted  :" + localDoubleMatrix1D);
/*     */     
/*     */ 
/* 578 */     localDoubleMatrix1D.assign(Functions.sin);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 586 */     System.out.println("sined  :" + localDoubleMatrix1D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected static void zdemo4()
/*     */   {
/* 592 */     double[] arrayOfDouble1 = { 0.0D, 1.0D, 2.0D, 3.0D };
/* 593 */     double[] arrayOfDouble2 = { 0.0D, 2.0D, 4.0D, 6.0D };
/* 594 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D1 = new DenseDoubleMatrix1D(arrayOfDouble1);
/* 595 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D2 = new DenseDoubleMatrix1D(arrayOfDouble2);
/* 596 */     System.out.println("m1:" + localDenseDoubleMatrix1D1);
/* 597 */     System.out.println("m2:" + localDenseDoubleMatrix1D2);
/*     */     
/* 599 */     localDenseDoubleMatrix1D1.assign(localDenseDoubleMatrix1D2, Functions.pow);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 609 */     System.out.println("applied:" + localDenseDoubleMatrix1D1);
/*     */   }
/*     */   
/*     */ 
/*     */   public static void zdemo5(int paramInt1, int paramInt2, boolean paramBoolean)
/*     */   {
/* 615 */     Sorting localSorting = quickSort;
/*     */     
/*     */ 
/* 618 */     System.out.println("\n\n");
/* 619 */     System.out.print("now initializing... ");
/* 620 */     Timer localTimer = new Timer().start();
/*     */     
/* 622 */     Functions localFunctions = Functions.functions;
/* 623 */     Object localObject = DoubleFactory2D.dense.make(paramInt1, paramInt2);
/* 624 */     ((DoubleMatrix2D)localObject).assign(new DRand());
/* 625 */     localTimer.stop().display();
/*     */     
/*     */ 
/* 628 */     DoubleMatrix2D localDoubleMatrix2D = ((DoubleMatrix2D)localObject).like();
/* 629 */     localTimer.reset().start();
/* 630 */     System.out.print("now copying... ");
/* 631 */     localDoubleMatrix2D.assign((DoubleMatrix2D)localObject);
/* 632 */     localTimer.stop().display();
/*     */     
/* 634 */     localTimer.reset().start();
/* 635 */     System.out.print("now copying subrange... ");
/* 636 */     localDoubleMatrix2D.viewPart(0, 0, paramInt1, paramInt2).assign(((DoubleMatrix2D)localObject).viewPart(0, 0, paramInt1, paramInt2));
/* 637 */     localTimer.stop().display();
/*     */     
/*     */ 
/* 640 */     localTimer.reset().start();
/* 641 */     System.out.print("now copying selected... ");
/* 642 */     localDoubleMatrix2D.viewSelection(null, null).assign(((DoubleMatrix2D)localObject).viewSelection(null, null));
/* 643 */     localTimer.stop().display();
/*     */     
/* 645 */     System.out.print("now sorting - quick version with precomputation... ");
/* 646 */     localTimer.reset().start();
/*     */     
/* 648 */     localObject = localSorting.sort((DoubleMatrix2D)localObject, BinFunctions1D.median);
/*     */     
/* 650 */     localTimer.stop().display();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 655 */     if (paramBoolean) {
/* 656 */       int i = Math.min(paramInt1, 5);
/* 657 */       BinFunction1D[] arrayOfBinFunction1D = { BinFunctions1D.median, BinFunctions1D.sumLog, BinFunctions1D.geometricMean };
/* 658 */       String[] arrayOfString1 = new String[i];
/* 659 */       String[] arrayOfString2 = new String[paramInt2];
/* 660 */       int j = paramInt2; do { arrayOfString2[j] = Integer.toString(j);j--; } while (j >= 0);
/* 661 */       int k = i; do { arrayOfString1[k] = Integer.toString(k);k--; } while (k >= 0);
/* 662 */       System.out.println("first part of sorted result = \n" + new Formatter("%G").toTitleString(((DoubleMatrix2D)localObject).viewPart(0, 0, i, paramInt2), arrayOfString1, arrayOfString2, null, null, null, arrayOfBinFunction1D));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 668 */     System.out.print("now sorting - slow version... ");
/* 669 */     localObject = localDoubleMatrix2D;
/* 670 */     DoubleMatrix1DComparator local13 = new DoubleMatrix1DComparator() {
/*     */       public int compare(DoubleMatrix1D paramAnonymousDoubleMatrix1D1, DoubleMatrix1D paramAnonymousDoubleMatrix1D2) {
/* 672 */         double d1 = Statistic.bin(paramAnonymousDoubleMatrix1D1).median();
/* 673 */         double d2 = Statistic.bin(paramAnonymousDoubleMatrix1D2).median();
/*     */         
/*     */ 
/* 676 */         return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
/*     */       }
/* 678 */     };
/* 679 */     localTimer.reset().start();
/* 680 */     localObject = localSorting.sort((DoubleMatrix2D)localObject, local13);
/* 681 */     localTimer.stop().display();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void zdemo6()
/*     */   {
/* 688 */     Sorting localSorting = quickSort;
/* 689 */     double[][] arrayOfDouble = { { 3.0D, 7.0D, 0.0D }, { 2.0D, 1.0D, 0.0D }, { 2.0D, 2.0D, 0.0D }, { 1.0D, 8.0D, 0.0D }, { 2.0D, 5.0D, 0.0D }, { 7.0D, 0.0D, 0.0D }, { 2.0D, 3.0D, 0.0D }, { 1.0D, 0.0D, 0.0D }, { 4.0D, 0.0D, 0.0D }, { 2.0D, 0.0D, 0.0D } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 701 */     DoubleMatrix2D localDoubleMatrix2D1 = DoubleFactory2D.dense.make(arrayOfDouble);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 711 */     System.out.println("\n\nunsorted:" + localDoubleMatrix2D1);
/* 712 */     DoubleMatrix2D localDoubleMatrix2D2 = quickSort.sort(localDoubleMatrix2D1, 1);
/* 713 */     DoubleMatrix2D localDoubleMatrix2D3 = quickSort.sort(localDoubleMatrix2D2, 0);
/* 714 */     System.out.println("quick sorted  :" + localDoubleMatrix2D3);
/*     */     
/* 716 */     localDoubleMatrix2D2 = mergeSort.sort(localDoubleMatrix2D1, 1);
/* 717 */     localDoubleMatrix2D3 = mergeSort.sort(localDoubleMatrix2D2, 0);
/* 718 */     System.out.println("merge sorted  :" + localDoubleMatrix2D3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void zdemo7(int paramInt1, int paramInt2, boolean paramBoolean)
/*     */   {
/* 727 */     System.out.println("\n\n");
/* 728 */     System.out.println("now initializing... ");
/*     */     
/* 730 */     Functions localFunctions = Functions.functions;
/* 731 */     DoubleMatrix2D localDoubleMatrix2D1 = DoubleFactory2D.dense.make(paramInt1, paramInt2);
/* 732 */     localDoubleMatrix2D1.assign(new DRand());
/* 733 */     DoubleMatrix2D localDoubleMatrix2D2 = localDoubleMatrix2D1.copy();
/*     */     
/* 735 */     double[] arrayOfDouble1 = localDoubleMatrix2D1.viewColumn(0).toArray();
/* 736 */     double[] arrayOfDouble2 = localDoubleMatrix2D1.viewColumn(0).toArray();
/* 737 */     System.out.print("now quick sorting... ");
/* 738 */     Timer localTimer = new Timer().start();
/* 739 */     quickSort.sort(localDoubleMatrix2D1, 0);
/* 740 */     localTimer.stop().display();
/*     */     
/* 742 */     System.out.print("now merge sorting... ");
/* 743 */     localTimer.reset().start();
/* 744 */     mergeSort.sort(localDoubleMatrix2D1, 0);
/* 745 */     localTimer.stop().display();
/*     */     
/* 747 */     System.out.print("now quick sorting with simple aggregation... ");
/* 748 */     localTimer.reset().start();
/* 749 */     quickSort.sort(localDoubleMatrix2D1, arrayOfDouble1);
/* 750 */     localTimer.stop().display();
/*     */     
/* 752 */     System.out.print("now merge sorting with simple aggregation... ");
/* 753 */     localTimer.reset().start();
/* 754 */     mergeSort.sort(localDoubleMatrix2D1, arrayOfDouble2);
/* 755 */     localTimer.stop().display();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/doublealgo/Sorting.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */