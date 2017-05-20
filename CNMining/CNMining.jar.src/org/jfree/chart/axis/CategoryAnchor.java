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
/*     */ public final class CategoryAnchor
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2604142742210173810L;
/*  56 */   public static final CategoryAnchor START = new CategoryAnchor("CategoryAnchor.START");
/*     */   
/*     */ 
/*     */ 
/*  60 */   public static final CategoryAnchor MIDDLE = new CategoryAnchor("CategoryAnchor.MIDDLE");
/*     */   
/*     */ 
/*     */ 
/*  64 */   public static final CategoryAnchor END = new CategoryAnchor("CategoryAnchor.END");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private CategoryAnchor(String name)
/*     */   {
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
/* 101 */     if (!(obj instanceof CategoryAnchor)) {
/* 102 */       return false;
/*     */     }
/* 104 */     CategoryAnchor position = (CategoryAnchor)obj;
/* 105 */     if (!this.name.equals(position.toString())) {
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
/* 120 */     if (equals(START)) {
/* 121 */       return START;
/*     */     }
/* 123 */     if (equals(MIDDLE)) {
/* 124 */       return MIDDLE;
/*     */     }
/* 126 */     if (equals(END)) {
/* 127 */       return END;
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/CategoryAnchor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */