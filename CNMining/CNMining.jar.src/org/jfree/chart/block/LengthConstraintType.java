/*     */ package org.jfree.chart.block;
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
/*     */ public final class LengthConstraintType
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1156658804028142978L;
/*  55 */   public static final LengthConstraintType NONE = new LengthConstraintType("LengthConstraintType.NONE");
/*     */   
/*     */ 
/*     */ 
/*  59 */   public static final LengthConstraintType RANGE = new LengthConstraintType("RectangleConstraintType.RANGE");
/*     */   
/*     */ 
/*     */ 
/*  63 */   public static final LengthConstraintType FIXED = new LengthConstraintType("LengthConstraintType.FIXED");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private LengthConstraintType(String name)
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
/*     */   public boolean equals(Object obj)
/*     */   {
/*  96 */     if (this == obj) {
/*  97 */       return true;
/*     */     }
/*  99 */     if (!(obj instanceof LengthConstraintType)) {
/* 100 */       return false;
/*     */     }
/* 102 */     LengthConstraintType that = (LengthConstraintType)obj;
/* 103 */     if (!this.name.equals(that.toString())) {
/* 104 */       return false;
/*     */     }
/* 106 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 115 */     return this.name.hashCode();
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
/* 126 */     if (equals(NONE)) {
/* 127 */       return NONE;
/*     */     }
/* 129 */     if (equals(RANGE)) {
/* 130 */       return RANGE;
/*     */     }
/* 132 */     if (equals(FIXED)) {
/* 133 */       return FIXED;
/*     */     }
/* 135 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/LengthConstraintType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */