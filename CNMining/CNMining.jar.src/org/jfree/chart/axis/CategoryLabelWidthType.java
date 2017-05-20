/*     */ package org.jfree.chart.axis;
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
/*     */ public final class CategoryLabelWidthType
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6976024792582949656L;
/*  56 */   public static final CategoryLabelWidthType CATEGORY = new CategoryLabelWidthType("CategoryLabelWidthType.CATEGORY");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  62 */   public static final CategoryLabelWidthType RANGE = new CategoryLabelWidthType("CategoryLabelWidthType.RANGE");
/*     */   
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private CategoryLabelWidthType(String name)
/*     */   {
/*  73 */     if (name == null) {
/*  74 */       throw new IllegalArgumentException("Null 'name' argument.");
/*     */     }
/*  76 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  85 */     return this.name;
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
/*  98 */     if (this == obj) {
/*  99 */       return true;
/*     */     }
/* 101 */     if (!(obj instanceof CategoryLabelWidthType)) {
/* 102 */       return false;
/*     */     }
/* 104 */     CategoryLabelWidthType t = (CategoryLabelWidthType)obj;
/* 105 */     if (!this.name.equals(t.toString())) {
/* 106 */       return false;
/*     */     }
/* 108 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */     throws ObjectStreamException
/*     */   {
/* 120 */     if (equals(CATEGORY)) {
/* 121 */       return CATEGORY;
/*     */     }
/* 123 */     if (equals(RANGE)) {
/* 124 */       return RANGE;
/*     */     }
/* 126 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/CategoryLabelWidthType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */