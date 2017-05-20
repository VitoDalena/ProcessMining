/*     */ package cern.colt.matrix.doublealgo;
/*     */ 
/*     */ import cern.colt.Swapper;
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Partitioning
/*     */ {
/*     */   public static void partition(DoubleMatrix2D paramDoubleMatrix2D, int[] paramArrayOfInt1, int paramInt1, int paramInt2, int paramInt3, double[] paramArrayOfDouble, int paramInt4, int paramInt5, int[] paramArrayOfInt2)
/*     */   {
/* 107 */     if ((paramInt1 < 0) || (paramInt2 >= paramDoubleMatrix2D.rows()) || (paramInt2 >= paramArrayOfInt1.length)) throw new IllegalArgumentException();
/* 108 */     if ((paramInt3 < 0) || (paramInt3 >= paramDoubleMatrix2D.columns())) throw new IllegalArgumentException();
/* 109 */     if ((paramInt4 < 0) || (paramInt5 >= paramArrayOfDouble.length)) throw new IllegalArgumentException();
/* 110 */     if (paramArrayOfInt2.length < paramArrayOfDouble.length) { throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 113 */     int[] arrayOfInt = paramArrayOfInt1;
/* 114 */     Swapper local1 = new Swapper() { private final int[] val$g;
/*     */       
/* 116 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { int i = this.val$g[paramAnonymousInt1];this.val$g[paramAnonymousInt1] = this.val$g[paramAnonymousInt2];this.val$g[paramAnonymousInt2] = i;
/*     */       }
/*     */       
/*     */ 
/* 120 */     };
/* 121 */     DoubleMatrix1D localDoubleMatrix1D = paramDoubleMatrix2D.viewColumn(paramInt3);
/* 122 */     IntComparator local2 = new IntComparator() { private final double[] val$splitters;
/*     */       
/* 124 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { double d1 = this.val$splitters[paramAnonymousInt1];
/* 125 */         double d2 = this.val$columnView.getQuick(this.val$g[paramAnonymousInt2]);
/* 126 */         return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
/*     */       }
/*     */       
/*     */ 
/* 130 */     };
/* 131 */     IntComparator local3 = new IntComparator() { private final DoubleMatrix1D val$columnView;
/*     */       
/* 133 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { double d1 = this.val$columnView.getQuick(this.val$g[paramAnonymousInt1]);
/* 134 */         double d2 = this.val$columnView.getQuick(this.val$g[paramAnonymousInt2]);
/* 135 */         return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
/*     */       }
/*     */       
/*     */ 
/* 139 */     };
/* 140 */     IntComparator local4 = new IntComparator() { private final int[] val$g;
/*     */       
/* 142 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { double d1 = this.val$splitters[paramAnonymousInt1];
/* 143 */         double d2 = this.val$splitters[paramAnonymousInt2];
/* 144 */         return d1 == d2 ? 0 : d1 < d2 ? -1 : 1;
/*     */       }
/*     */       
/*     */ 
/* 148 */     };
/* 149 */     cern.colt.Partitioning.genericPartition(paramInt1, paramInt2, paramInt4, paramInt5, paramArrayOfInt2, local2, local3, local4, local1);
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
/*     */   public static DoubleMatrix2D partition(DoubleMatrix2D paramDoubleMatrix2D, int paramInt, double[] paramArrayOfDouble, int[] paramArrayOfInt)
/*     */   {
/* 211 */     int i = 0;
/* 212 */     int j = paramDoubleMatrix2D.rows() - 1;
/* 213 */     int k = 0;
/* 214 */     int m = paramArrayOfDouble.length - 1;
/* 215 */     int[] arrayOfInt1 = new int[paramDoubleMatrix2D.rows()];
/* 216 */     int n = arrayOfInt1.length; do { arrayOfInt1[n] = n;n--; } while (n >= 0);
/*     */     
/* 218 */     partition(paramDoubleMatrix2D, arrayOfInt1, i, j, paramInt, paramArrayOfDouble, k, m, paramArrayOfInt);
/*     */     
/*     */ 
/* 221 */     int[] arrayOfInt2 = new int[paramDoubleMatrix2D.columns()];
/* 222 */     int i1 = arrayOfInt2.length; do { arrayOfInt2[i1] = i1;i1--; } while (i1 >= 0);
/*     */     
/*     */ 
/* 225 */     return paramDoubleMatrix2D.viewSelection(arrayOfInt1, arrayOfInt2);
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
/*     */   private final DoubleMatrix1D val$columnView;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final int[] val$g;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final double[] val$splitters;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void xPartitionOld(DoubleMatrix2D paramDoubleMatrix2D, DoubleMatrix1D paramDoubleMatrix1D, int paramInt1, int paramInt2, double[] paramArrayOfDouble, int paramInt3, int paramInt4, int[] paramArrayOfInt) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static int xPartitionOld(DoubleMatrix2D paramDoubleMatrix2D, DoubleMatrix1D paramDoubleMatrix1D, int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 384 */     return 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/doublealgo/Partitioning.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */