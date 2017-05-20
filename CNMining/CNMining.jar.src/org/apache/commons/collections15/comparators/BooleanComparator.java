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
/*     */ public final class BooleanComparator
/*     */   implements Comparator<Boolean>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1830042991606340609L;
/*  44 */   private static final BooleanComparator TRUE_FIRST = new BooleanComparator(true);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  49 */   private static final BooleanComparator FALSE_FIRST = new BooleanComparator(false);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  54 */   private boolean trueFirst = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static BooleanComparator getTrueFirstComparator()
/*     */   {
/*  70 */     return TRUE_FIRST;
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
/*     */   public static BooleanComparator getFalseFirstComparator()
/*     */   {
/*  86 */     return FALSE_FIRST;
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
/*     */   public static BooleanComparator getBooleanComparator(boolean trueFirst)
/*     */   {
/* 105 */     return trueFirst ? TRUE_FIRST : FALSE_FIRST;
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
/*     */   public BooleanComparator()
/*     */   {
/* 118 */     this(false);
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
/*     */   public BooleanComparator(boolean trueFirst)
/*     */   {
/* 132 */     this.trueFirst = trueFirst;
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
/*     */   public int compare(Boolean b1, Boolean b2)
/*     */   {
/* 145 */     boolean v1 = b1.booleanValue();
/* 146 */     boolean v2 = b2.booleanValue();
/*     */     
/* 148 */     return (v1 ^ v2) ? -1 : (v1 ^ this.trueFirst) ? 1 : 0;
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
/* 159 */     int hash = "BooleanComparator".hashCode();
/* 160 */     return this.trueFirst ? -1 * hash : hash;
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
/*     */   public boolean equals(Object object)
/*     */   {
/* 176 */     return (this == object) || (((object instanceof BooleanComparator)) && (this.trueFirst == ((BooleanComparator)object).trueFirst));
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
/*     */   public boolean sortsTrueFirst()
/*     */   {
/* 191 */     return this.trueFirst;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/comparators/BooleanComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */