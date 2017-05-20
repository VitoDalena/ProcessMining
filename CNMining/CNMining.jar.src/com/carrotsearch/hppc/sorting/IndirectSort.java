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
/*     */ public final class IndirectSort
/*     */ {
/*  19 */   static int MIN_LENGTH_FOR_INSERTION_SORT = 30;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int[] mergesort(int start, int length, IndirectComparator comparator)
/*     */   {
/*  38 */     int[] src = createOrderArray(start, length);
/*     */     
/*  40 */     if (length > 1)
/*     */     {
/*  42 */       int[] dst = (int[])src.clone();
/*  43 */       topDownMergeSort(src, dst, 0, length, comparator);
/*  44 */       return dst;
/*     */     }
/*     */     
/*  47 */     return src;
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
/*     */   public static <T> int[] mergesort(T[] input, int start, int length, Comparator<? super T> comparator)
/*     */   {
/*  62 */     return mergesort(start, length, new IndirectComparator.DelegatingComparator(input, comparator));
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
/*     */   private static void topDownMergeSort(int[] src, int[] dst, int fromIndex, int toIndex, IndirectComparator comp)
/*     */   {
/*  75 */     if (toIndex - fromIndex <= MIN_LENGTH_FOR_INSERTION_SORT)
/*     */     {
/*  77 */       insertionSort(fromIndex, toIndex - fromIndex, dst, comp);
/*  78 */       return;
/*     */     }
/*     */     
/*  81 */     int mid = fromIndex + toIndex >>> 1;
/*  82 */     topDownMergeSort(dst, src, fromIndex, mid, comp);
/*  83 */     topDownMergeSort(dst, src, mid, toIndex, comp);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  88 */     if (comp.compare(src[(mid - 1)], src[mid]) <= 0)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  94 */       System.arraycopy(src, fromIndex, dst, fromIndex, toIndex - fromIndex);
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 101 */       int i = fromIndex;int j = mid; for (int k = fromIndex; k < toIndex; k++)
/*     */       {
/* 103 */         if ((j == toIndex) || ((i < mid) && (comp.compare(src[i], src[j]) <= 0)))
/*     */         {
/* 105 */           dst[k] = src[(i++)];
/*     */         }
/*     */         else
/*     */         {
/* 109 */           dst[k] = src[(j++)];
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void insertionSort(int off, int len, int[] order, IndirectComparator intComparator)
/*     */   {
/* 121 */     for (int i = off + 1; i < off + len; i++)
/*     */     {
/* 123 */       int v = order[i];
/* 124 */       int j = i;
/* 125 */       int t; while ((j > off) && (intComparator.compare(t = order[(j - 1)], v) > 0))
/*     */       {
/* 127 */         order[(j--)] = t;
/*     */       }
/* 129 */       order[j] = v;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static int[] createOrderArray(int start, int length)
/*     */   {
/* 138 */     int[] order = new int[length];
/* 139 */     for (int i = 0; i < length; i++)
/*     */     {
/* 141 */       order[i] = (start + i);
/*     */     }
/* 143 */     return order;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/sorting/IndirectSort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */