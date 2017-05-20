/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RangeType
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -9073319010650549239L;
/*  56 */   public static final RangeType FULL = new RangeType("RangeType.FULL");
/*     */   
/*     */ 
/*  59 */   public static final RangeType POSITIVE = new RangeType("RangeType.POSITIVE");
/*     */   
/*     */ 
/*     */ 
/*  63 */   public static final RangeType NEGATIVE = new RangeType("RangeType.NEGATIVE");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private RangeType(String name)
/*     */   {
/*  75 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  84 */     return this.name;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/*  97 */     if (this == obj) {
/*  98 */       return true;
/*     */     }
/* 100 */     if (!(obj instanceof RangeType)) {
/* 101 */       return false;
/*     */     }
/* 103 */     RangeType that = (RangeType)obj;
/* 104 */     if (!this.name.equals(that.toString())) {
/* 105 */       return false;
/*     */     }
/* 107 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 116 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */     throws ObjectStreamException
/*     */   {
/* 127 */     if (equals(FULL)) {
/* 128 */       return FULL;
/*     */     }
/* 130 */     if (equals(POSITIVE)) {
/* 131 */       return POSITIVE;
/*     */     }
/* 133 */     if (equals(NEGATIVE)) {
/* 134 */       return NEGATIVE;
/*     */     }
/* 136 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/RangeType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */