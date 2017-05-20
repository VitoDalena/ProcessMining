/*     */ package com.carrotsearch.hppc.sorting;
/*     */ 
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
/*     */ public abstract interface IndirectComparator
/*     */ {
/*     */   public abstract int compare(int paramInt1, int paramInt2);
/*     */   
/*     */   public static class AscendingIntComparator
/*     */     implements IndirectComparator
/*     */   {
/*     */     private final int[] array;
/*     */     
/*     */     public AscendingIntComparator(int[] array)
/*     */     {
/*  30 */       this.array = array;
/*     */     }
/*     */     
/*     */     public int compare(int indexA, int indexB)
/*     */     {
/*  35 */       int a = this.array[indexA];
/*  36 */       int b = this.array[indexB];
/*     */       
/*  38 */       if (a < b) return -1;
/*  39 */       if (a > b) return 1;
/*  40 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class DescendingIntComparator
/*     */     extends IndirectComparator.AscendingIntComparator
/*     */   {
/*     */     public DescendingIntComparator(int[] array)
/*     */     {
/*  51 */       super();
/*     */     }
/*     */     
/*     */     public final int compare(int indexA, int indexB)
/*     */     {
/*  56 */       return -super.compare(indexA, indexB);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class AscendingShortComparator
/*     */     implements IndirectComparator
/*     */   {
/*     */     private final short[] array;
/*     */     
/*     */ 
/*     */     public AscendingShortComparator(short[] array)
/*     */     {
/*  69 */       this.array = array;
/*     */     }
/*     */     
/*     */     public int compare(int indexA, int indexB)
/*     */     {
/*  74 */       short a = this.array[indexA];
/*  75 */       short b = this.array[indexB];
/*     */       
/*  77 */       if (a < b) return -1;
/*  78 */       if (a > b) return 1;
/*  79 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class DescendingShortComparator
/*     */     extends IndirectComparator.AscendingShortComparator
/*     */   {
/*     */     public DescendingShortComparator(short[] array)
/*     */     {
/*  90 */       super();
/*     */     }
/*     */     
/*     */     public final int compare(int indexA, int indexB)
/*     */     {
/*  95 */       return -super.compare(indexA, indexB);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class AscendingDoubleComparator
/*     */     implements IndirectComparator
/*     */   {
/*     */     private final double[] array;
/*     */     
/*     */ 
/*     */     public AscendingDoubleComparator(double[] array)
/*     */     {
/* 108 */       this.array = array;
/*     */     }
/*     */     
/*     */     public int compare(int indexA, int indexB)
/*     */     {
/* 113 */       double a = this.array[indexA];
/* 114 */       double b = this.array[indexB];
/*     */       
/* 116 */       if (a < b) return -1;
/* 117 */       if (a > b) return 1;
/* 118 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class DescendingDoubleComparator
/*     */     extends IndirectComparator.AscendingDoubleComparator
/*     */   {
/*     */     public DescendingDoubleComparator(double[] array)
/*     */     {
/* 129 */       super();
/*     */     }
/*     */     
/*     */     public final int compare(int indexA, int indexB)
/*     */     {
/* 134 */       return -super.compare(indexA, indexB);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class AscendingFloatComparator
/*     */     implements IndirectComparator
/*     */   {
/*     */     private final float[] array;
/*     */     
/*     */ 
/*     */     public AscendingFloatComparator(float[] array)
/*     */     {
/* 147 */       this.array = array;
/*     */     }
/*     */     
/*     */     public int compare(int indexA, int indexB)
/*     */     {
/* 152 */       float a = this.array[indexA];
/* 153 */       float b = this.array[indexB];
/*     */       
/* 155 */       if (a < b) return -1;
/* 156 */       if (a > b) return 1;
/* 157 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static class DescendingFloatComparator
/*     */     extends IndirectComparator.AscendingFloatComparator
/*     */   {
/*     */     public DescendingFloatComparator(float[] array)
/*     */     {
/* 168 */       super();
/*     */     }
/*     */     
/*     */     public final int compare(int indexA, int indexB)
/*     */     {
/* 173 */       return -super.compare(indexA, indexB);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static final class DelegatingComparator<T>
/*     */     implements IndirectComparator
/*     */   {
/*     */     private final T[] array;
/*     */     
/*     */     private final Comparator<? super T> delegate;
/*     */     
/*     */     public DelegatingComparator(T[] array, Comparator<? super T> delegate)
/*     */     {
/* 187 */       this.array = array;
/* 188 */       this.delegate = delegate;
/*     */     }
/*     */     
/*     */     public final int compare(int indexA, int indexB)
/*     */     {
/* 193 */       return this.delegate.compare(this.array[indexA], this.array[indexB]);
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 199 */       return getClass().getSimpleName() + " -> " + this.delegate;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/sorting/IndirectComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */