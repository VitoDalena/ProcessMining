/*     */ package cern.colt.matrix.objectalgo;
/*     */ 
/*     */ import cern.colt.Swapper;
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.matrix.ObjectMatrix1D;
/*     */ import cern.colt.matrix.ObjectMatrix2D;
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
/*     */   public static void partition(ObjectMatrix2D paramObjectMatrix2D, int[] paramArrayOfInt1, int paramInt1, int paramInt2, int paramInt3, Object[] paramArrayOfObject, int paramInt4, int paramInt5, int[] paramArrayOfInt2)
/*     */   {
/* 107 */     if ((paramInt1 < 0) || (paramInt2 >= paramObjectMatrix2D.rows()) || (paramInt2 >= paramArrayOfInt1.length)) throw new IllegalArgumentException();
/* 108 */     if ((paramInt3 < 0) || (paramInt3 >= paramObjectMatrix2D.columns())) throw new IllegalArgumentException();
/* 109 */     if ((paramInt4 < 0) || (paramInt5 >= paramArrayOfObject.length)) throw new IllegalArgumentException();
/* 110 */     if (paramArrayOfInt2.length < paramArrayOfObject.length) { throw new IllegalArgumentException();
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
/* 121 */     ObjectMatrix1D localObjectMatrix1D = paramObjectMatrix2D.viewColumn(paramInt3);
/* 122 */     IntComparator local2 = new IntComparator() { private final Object[] val$splitters;
/*     */       
/* 124 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { Comparable localComparable1 = (Comparable)this.val$splitters[paramAnonymousInt1];
/* 125 */         Comparable localComparable2 = (Comparable)this.val$columnView.getQuick(this.val$g[paramAnonymousInt2]);
/* 126 */         int i = localComparable1.compareTo(localComparable2);
/* 127 */         return i == 0 ? 0 : i < 0 ? -1 : 1;
/*     */       }
/*     */       
/*     */ 
/* 131 */     };
/* 132 */     IntComparator local3 = new IntComparator() { private final ObjectMatrix1D val$columnView;
/*     */       
/* 134 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { Comparable localComparable1 = (Comparable)this.val$columnView.getQuick(this.val$g[paramAnonymousInt1]);
/* 135 */         Comparable localComparable2 = (Comparable)this.val$columnView.getQuick(this.val$g[paramAnonymousInt2]);
/* 136 */         int i = localComparable1.compareTo(localComparable2);
/* 137 */         return i == 0 ? 0 : i < 0 ? -1 : 1;
/*     */       }
/*     */       
/*     */ 
/* 141 */     };
/* 142 */     IntComparator local4 = new IntComparator() { private final int[] val$g;
/*     */       
/* 144 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { Comparable localComparable1 = (Comparable)this.val$splitters[paramAnonymousInt1];
/* 145 */         Comparable localComparable2 = (Comparable)this.val$splitters[paramAnonymousInt2];
/* 146 */         int i = localComparable1.compareTo(localComparable2);
/* 147 */         return i == 0 ? 0 : i < 0 ? -1 : 1;
/*     */       }
/*     */       
/*     */ 
/* 151 */     };
/* 152 */     cern.colt.Partitioning.genericPartition(paramInt1, paramInt2, paramInt4, paramInt5, paramArrayOfInt2, local2, local3, local4, local1);
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
/*     */   public static ObjectMatrix2D partition(ObjectMatrix2D paramObjectMatrix2D, int paramInt, Object[] paramArrayOfObject, int[] paramArrayOfInt)
/*     */   {
/* 214 */     int i = 0;
/* 215 */     int j = paramObjectMatrix2D.rows() - 1;
/* 216 */     int k = 0;
/* 217 */     int m = paramArrayOfObject.length - 1;
/* 218 */     int[] arrayOfInt1 = new int[paramObjectMatrix2D.rows()];
/* 219 */     int n = arrayOfInt1.length; do { arrayOfInt1[n] = n;n--; } while (n >= 0);
/*     */     
/* 221 */     partition(paramObjectMatrix2D, arrayOfInt1, i, j, paramInt, paramArrayOfObject, k, m, paramArrayOfInt);
/*     */     
/*     */ 
/* 224 */     int[] arrayOfInt2 = new int[paramObjectMatrix2D.columns()];
/* 225 */     int i1 = arrayOfInt2.length; do { arrayOfInt2[i1] = i1;i1--; } while (i1 >= 0);
/*     */     
/*     */ 
/* 228 */     return paramObjectMatrix2D.viewSelection(arrayOfInt1, arrayOfInt2);
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
/*     */   private final ObjectMatrix1D val$columnView;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   private final Object[] val$splitters;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void xPartitionOld(ObjectMatrix2D paramObjectMatrix2D, ObjectMatrix1D paramObjectMatrix1D, int paramInt1, int paramInt2, Object[] paramArrayOfObject, int paramInt3, int paramInt4, int[] paramArrayOfInt) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static int xPartitionOld(ObjectMatrix2D paramObjectMatrix2D, ObjectMatrix1D paramObjectMatrix1D, int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 387 */     return 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/objectalgo/Partitioning.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */