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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NullComparator<T>
/*     */   implements Comparator<T>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5820772575483504339L;
/*     */   private Comparator<T> nonNullComparator;
/*     */   private boolean nullsAreHigh;
/*     */   
/*     */   public NullComparator(Comparator<T> nonNullComparator)
/*     */   {
/*  61 */     this(nonNullComparator, true);
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
/*     */   public NullComparator(Comparator<T> nonNullComparator, boolean nullsAreHigh)
/*     */   {
/*  82 */     this.nonNullComparator = nonNullComparator;
/*  83 */     this.nullsAreHigh = nullsAreHigh;
/*     */     
/*  85 */     if (nonNullComparator == null) {
/*  86 */       throw new NullPointerException("null nonNullComparator");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compare(T o1, T o2)
/*     */   {
/* 108 */     if (o1 == o2) {
/* 109 */       return 0;
/*     */     }
/* 111 */     if (o1 == null) {
/* 112 */       return this.nullsAreHigh ? 1 : -1;
/*     */     }
/* 114 */     if (o2 == null) {
/* 115 */       return this.nullsAreHigh ? -1 : 1;
/*     */     }
/* 117 */     return this.nonNullComparator.compare(o1, o2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 128 */     return (this.nullsAreHigh ? -1 : 1) * this.nonNullComparator.hashCode();
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 142 */     if (obj == null) {
/* 143 */       return false;
/*     */     }
/* 145 */     if (obj == this) {
/* 146 */       return true;
/*     */     }
/* 148 */     if (!obj.getClass().equals(getClass())) {
/* 149 */       return false;
/*     */     }
/*     */     
/* 152 */     NullComparator other = (NullComparator)obj;
/*     */     
/* 154 */     return (this.nullsAreHigh == other.nullsAreHigh) && (this.nonNullComparator.equals(other.nonNullComparator));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/comparators/NullComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */