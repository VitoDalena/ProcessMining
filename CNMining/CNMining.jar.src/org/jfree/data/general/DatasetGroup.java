/*     */ package org.jfree.data.general;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DatasetGroup
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3640642179674185688L;
/*     */   private String id;
/*     */   
/*     */   public DatasetGroup()
/*     */   {
/*  64 */     this.id = "NOID";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DatasetGroup(String id)
/*     */   {
/*  73 */     if (id == null) {
/*  74 */       throw new IllegalArgumentException("Null 'id' argument.");
/*     */     }
/*  76 */     this.id = id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getID()
/*     */   {
/*  85 */     return this.id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/*  96 */     return super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 107 */     if (obj == this) {
/* 108 */       return true;
/*     */     }
/* 110 */     if (!(obj instanceof DatasetGroup)) {
/* 111 */       return false;
/*     */     }
/* 113 */     DatasetGroup that = (DatasetGroup)obj;
/* 114 */     if (!this.id.equals(that.id)) {
/* 115 */       return false;
/*     */     }
/* 117 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/DatasetGroup.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */