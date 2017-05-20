/*     */ package org.jfree.data;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class KeyedValueComparatorType
/*     */ {
/*  51 */   public static final KeyedValueComparatorType BY_KEY = new KeyedValueComparatorType("KeyedValueComparatorType.BY_KEY");
/*     */   
/*     */ 
/*     */ 
/*  55 */   public static final KeyedValueComparatorType BY_VALUE = new KeyedValueComparatorType("KeyedValueComparatorType.BY_VALUE");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private KeyedValueComparatorType(String name)
/*     */   {
/*  67 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  76 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  88 */     if (this == o) {
/*  89 */       return true;
/*     */     }
/*  91 */     if (!(o instanceof KeyedValueComparatorType)) {
/*  92 */       return false;
/*     */     }
/*     */     
/*  95 */     KeyedValueComparatorType type = (KeyedValueComparatorType)o;
/*  96 */     if (!this.name.equals(type.name)) {
/*  97 */       return false;
/*     */     }
/*     */     
/* 100 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 109 */     return this.name.hashCode();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/KeyedValueComparatorType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */