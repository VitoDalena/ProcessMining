/*     */ package cern.colt.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractList
/*     */   extends AbstractCollection
/*     */ {
/*     */   public void addAllOf(Collection paramCollection)
/*     */   {
/*  36 */     beforeInsertAllOf(size(), paramCollection);
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
/*     */   public void beforeInsertAllOf(int paramInt, Collection paramCollection)
/*     */   {
/*  50 */     beforeInsertDummies(paramInt, paramCollection.size());
/*  51 */     replaceFromWith(paramInt, paramCollection);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void beforeInsertDummies(int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static void checkRange(int paramInt1, int paramInt2)
/*     */   {
/*  68 */     if ((paramInt1 >= paramInt2) || (paramInt1 < 0)) {
/*  69 */       throw new IndexOutOfBoundsException("Index: " + paramInt1 + ", Size: " + paramInt2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected static void checkRangeFromTo(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  76 */     if (paramInt2 == paramInt1 - 1) return;
/*  77 */     if ((paramInt1 < 0) || (paramInt1 > paramInt2) || (paramInt2 >= paramInt3)) {
/*  78 */       throw new IndexOutOfBoundsException("from: " + paramInt1 + ", to: " + paramInt2 + ", size=" + paramInt3);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void clear()
/*     */   {
/*  85 */     removeFromTo(0, size() - 1);
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
/*     */   public final void mergeSort()
/*     */   {
/* 102 */     mergeSortFromTo(0, size() - 1);
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
/*     */   public abstract void mergeSortFromTo(int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void quickSort()
/*     */   {
/* 136 */     quickSortFromTo(0, size() - 1);
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
/*     */   public abstract void quickSortFromTo(int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove(int paramInt)
/*     */   {
/* 163 */     removeFromTo(paramInt, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void removeFromTo(int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void replaceFromWith(int paramInt, Collection paramCollection);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void reverse();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(int paramInt)
/*     */   {
/* 200 */     if (paramInt < 0) { throw new IndexOutOfBoundsException("newSize:" + paramInt);
/*     */     }
/* 202 */     int i = size();
/* 203 */     if (paramInt != i) {
/* 204 */       if (paramInt > i) { beforeInsertDummies(i, paramInt - i);
/* 205 */       } else if (paramInt < i) { removeFromTo(paramInt, i - 1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final void shuffle()
/*     */   {
/* 212 */     shuffleFromTo(0, size() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void shuffleFromTo(int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void sort()
/*     */   {
/* 230 */     sortFromTo(0, size() - 1);
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
/*     */   public void sortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 244 */     quickSortFromTo(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   public void trimToSize() {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/AbstractList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */