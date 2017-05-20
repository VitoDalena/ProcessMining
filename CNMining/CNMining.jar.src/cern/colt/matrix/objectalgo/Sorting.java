/*     */ package cern.colt.matrix.objectalgo;
/*     */ 
/*     */ import cern.colt.GenericSorting;
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Swapper;
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.matrix.ObjectMatrix1D;
/*     */ import cern.colt.matrix.ObjectMatrix2D;
/*     */ import cern.colt.matrix.ObjectMatrix3D;
/*     */ import cern.colt.matrix.impl.AbstractFormatter;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix3D;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   protected void runSort(int[] paramArrayOfInt, int paramInt1, int paramInt2, IntComparator paramIntComparator)
/*     */   {
/*  60 */     cern.colt.Sorting.quickSort(paramArrayOfInt, paramInt1, paramInt2, paramIntComparator);
/*     */   }
/*     */   
/*  63 */   protected void runSort(int paramInt1, int paramInt2, IntComparator paramIntComparator, Swapper paramSwapper) { GenericSorting.quickSort(paramInt1, paramInt2, paramIntComparator, paramSwapper); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D sort(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/*  88 */     int[] arrayOfInt = new int[paramObjectMatrix1D.size()];
/*  89 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/*  91 */     IntComparator local2 = new IntComparator() { private final ObjectMatrix1D val$vector;
/*     */       
/*  93 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { Comparable localComparable1 = (Comparable)this.val$vector.getQuick(paramAnonymousInt1);
/*  94 */         Comparable localComparable2 = (Comparable)this.val$vector.getQuick(paramAnonymousInt2);
/*  95 */         int i = localComparable1.compareTo(localComparable2);
/*  96 */         return i > 0 ? 1 : i < 0 ? -1 : 0;
/*     */       }
/*     */       
/*  99 */     };
/* 100 */     runSort(arrayOfInt, 0, arrayOfInt.length, local2);
/*     */     
/* 102 */     return paramObjectMatrix1D.viewSelection(arrayOfInt);
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
/*     */   public ObjectMatrix1D sort(ObjectMatrix1D paramObjectMatrix1D, Comparator paramComparator)
/*     */   {
/* 128 */     int[] arrayOfInt = new int[paramObjectMatrix1D.size()];
/* 129 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/* 131 */     IntComparator local3 = new IntComparator() { private final Comparator val$c;
/*     */       
/* 133 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$c.compare(this.val$vector.getQuick(paramAnonymousInt1), this.val$vector.getQuick(paramAnonymousInt2));
/*     */       }
/*     */ 
/* 136 */     };
/* 137 */     runSort(arrayOfInt, 0, arrayOfInt.length, local3);
/*     */     
/* 139 */     return paramObjectMatrix1D.viewSelection(arrayOfInt);
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
/*     */   private final ObjectMatrix1D val$vector;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D sort(ObjectMatrix2D paramObjectMatrix2D, int paramInt)
/*     */   {
/* 180 */     if ((paramInt < 0) || (paramInt >= paramObjectMatrix2D.columns())) { throw new IndexOutOfBoundsException("column=" + paramInt + ", matrix=" + AbstractFormatter.shape(paramObjectMatrix2D));
/*     */     }
/* 182 */     int[] arrayOfInt = new int[paramObjectMatrix2D.rows()];
/* 183 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/* 185 */     ObjectMatrix1D localObjectMatrix1D = paramObjectMatrix2D.viewColumn(paramInt);
/* 186 */     IntComparator local4 = new IntComparator() { private final ObjectMatrix1D val$col;
/*     */       
/* 188 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { Comparable localComparable1 = (Comparable)this.val$col.getQuick(paramAnonymousInt1);
/* 189 */         Comparable localComparable2 = (Comparable)this.val$col.getQuick(paramAnonymousInt2);
/* 190 */         int i = localComparable1.compareTo(localComparable2);
/* 191 */         return i > 0 ? 1 : i < 0 ? -1 : 0;
/*     */       }
/*     */       
/* 194 */     };
/* 195 */     runSort(arrayOfInt, 0, arrayOfInt.length, local4);
/*     */     
/*     */ 
/*     */ 
/* 199 */     return paramObjectMatrix2D.viewSelection(arrayOfInt, null);
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
/*     */   public ObjectMatrix2D sort(ObjectMatrix2D paramObjectMatrix2D, ObjectMatrix1DComparator paramObjectMatrix1DComparator)
/*     */   {
/* 225 */     int[] arrayOfInt = new int[paramObjectMatrix2D.rows()];
/* 226 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/* 228 */     ObjectMatrix1D[] arrayOfObjectMatrix1D = new ObjectMatrix1D[paramObjectMatrix2D.rows()];
/* 229 */     int j = arrayOfObjectMatrix1D.length; do { arrayOfObjectMatrix1D[j] = paramObjectMatrix2D.viewRow(j);j--; } while (j >= 0);
/*     */     
/* 231 */     IntComparator local5 = new IntComparator() { private final ObjectMatrix1DComparator val$c;
/*     */       private final ObjectMatrix1D[] val$views;
/*     */       
/* 234 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$c.compare(this.val$views[paramAnonymousInt1], this.val$views[paramAnonymousInt2]);
/*     */       }
/*     */ 
/* 237 */     };
/* 238 */     runSort(arrayOfInt, 0, arrayOfInt.length, local5);
/*     */     
/*     */ 
/*     */ 
/* 242 */     return paramObjectMatrix2D.viewSelection(arrayOfInt, null);
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
/*     */   public ObjectMatrix3D sort(ObjectMatrix3D paramObjectMatrix3D, int paramInt1, int paramInt2)
/*     */   {
/* 266 */     if ((paramInt1 < 0) || (paramInt1 >= paramObjectMatrix3D.rows())) throw new IndexOutOfBoundsException("row=" + paramInt1 + ", matrix=" + AbstractFormatter.shape(paramObjectMatrix3D));
/* 267 */     if ((paramInt2 < 0) || (paramInt2 >= paramObjectMatrix3D.columns())) { throw new IndexOutOfBoundsException("column=" + paramInt2 + ", matrix=" + AbstractFormatter.shape(paramObjectMatrix3D));
/*     */     }
/* 269 */     int[] arrayOfInt = new int[paramObjectMatrix3D.slices()];
/* 270 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/* 272 */     ObjectMatrix1D localObjectMatrix1D = paramObjectMatrix3D.viewRow(paramInt1).viewColumn(paramInt2);
/* 273 */     IntComparator local6 = new IntComparator() { private final ObjectMatrix1D val$sliceView;
/*     */       
/* 275 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { Comparable localComparable1 = (Comparable)this.val$sliceView.getQuick(paramAnonymousInt1);
/* 276 */         Comparable localComparable2 = (Comparable)this.val$sliceView.getQuick(paramAnonymousInt2);
/* 277 */         int i = localComparable1.compareTo(localComparable2);
/* 278 */         return i > 0 ? 1 : i < 0 ? -1 : 0;
/*     */       }
/*     */       
/* 281 */     };
/* 282 */     runSort(arrayOfInt, 0, arrayOfInt.length, local6);
/*     */     
/*     */ 
/*     */ 
/* 286 */     return paramObjectMatrix3D.viewSelection(arrayOfInt, null, null);
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
/*     */   public ObjectMatrix3D sort(ObjectMatrix3D paramObjectMatrix3D, ObjectMatrix2DComparator paramObjectMatrix2DComparator)
/*     */   {
/* 312 */     int[] arrayOfInt = new int[paramObjectMatrix3D.slices()];
/* 313 */     int i = arrayOfInt.length; do { arrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     
/* 315 */     ObjectMatrix2D[] arrayOfObjectMatrix2D = new ObjectMatrix2D[paramObjectMatrix3D.slices()];
/* 316 */     int j = arrayOfObjectMatrix2D.length; do { arrayOfObjectMatrix2D[j] = paramObjectMatrix3D.viewSlice(j);j--; } while (j >= 0);
/*     */     
/* 318 */     IntComparator local7 = new IntComparator() { private final ObjectMatrix2DComparator val$c;
/*     */       private final ObjectMatrix2D[] val$views;
/*     */       
/* 321 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$c.compare(this.val$views[paramAnonymousInt1], this.val$views[paramAnonymousInt2]);
/*     */       }
/*     */ 
/* 324 */     };
/* 325 */     runSort(arrayOfInt, 0, arrayOfInt.length, local7);
/*     */     
/*     */ 
/*     */ 
/* 329 */     return paramObjectMatrix3D.viewSelection(arrayOfInt, null, null);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/objectalgo/Sorting.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */