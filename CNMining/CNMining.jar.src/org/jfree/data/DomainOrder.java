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
/*     */ public final class DomainOrder
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4902774943512072627L;
/*  55 */   public static final DomainOrder NONE = new DomainOrder("DomainOrder.NONE");
/*     */   
/*     */ 
/*  58 */   public static final DomainOrder ASCENDING = new DomainOrder("DomainOrder.ASCENDING");
/*     */   
/*     */ 
/*     */ 
/*  62 */   public static final DomainOrder DESCENDING = new DomainOrder("DomainOrder.DESCENDING");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private DomainOrder(String name)
/*     */   {
/*  74 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  83 */     return this.name;
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
/*  96 */     if (this == obj) {
/*  97 */       return true;
/*     */     }
/*  99 */     if (!(obj instanceof DomainOrder)) {
/* 100 */       return false;
/*     */     }
/* 102 */     DomainOrder that = (DomainOrder)obj;
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
/* 126 */     if (equals(ASCENDING)) {
/* 127 */       return ASCENDING;
/*     */     }
/* 129 */     if (equals(DESCENDING)) {
/* 130 */       return DESCENDING;
/*     */     }
/* 132 */     if (equals(NONE)) {
/* 133 */       return NONE;
/*     */     }
/* 135 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/DomainOrder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */