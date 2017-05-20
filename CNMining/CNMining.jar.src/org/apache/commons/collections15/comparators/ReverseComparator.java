/*     */ package org.apache.commons.collections15.comparators;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReverseComparator<T>
/*     */   implements Comparator<T>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2858887242028539265L;
/*     */   private Comparator<T> comparator;
/*     */   
/*     */   public ReverseComparator(Comparator<T> comparator)
/*     */   {
/*  53 */     this.comparator = comparator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compare(T obj1, T obj2)
/*     */   {
/*  65 */     return this.comparator.compare(obj2, obj1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  77 */     return "ReverseComparator".hashCode() ^ this.comparator.hashCode();
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
/*     */   public boolean equals(Object object)
/*     */   {
/*  97 */     if (this == object)
/*  98 */       return true;
/*  99 */     if (null == object)
/* 100 */       return false;
/* 101 */     if (object.getClass().equals(getClass())) {
/* 102 */       ReverseComparator thatrc = (ReverseComparator)object;
/* 103 */       return this.comparator.equals(thatrc.comparator);
/*     */     }
/* 105 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/comparators/ReverseComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */