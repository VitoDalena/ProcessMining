/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyedObject
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2677930479256885863L;
/*     */   private Comparable key;
/*     */   private Object object;
/*     */   
/*     */   public KeyedObject(Comparable key, Object object)
/*     */   {
/*  71 */     this.key = key;
/*  72 */     this.object = object;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getKey()
/*     */   {
/*  81 */     return this.key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getObject()
/*     */   {
/*  90 */     return this.object;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setObject(Object object)
/*     */   {
/*  99 */     this.object = object;
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
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 113 */     KeyedObject clone = (KeyedObject)super.clone();
/* 114 */     if ((this.object instanceof PublicCloneable)) {
/* 115 */       PublicCloneable pc = (PublicCloneable)this.object;
/* 116 */       clone.object = pc.clone();
/*     */     }
/* 118 */     return clone;
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
/* 130 */     if (obj == this) {
/* 131 */       return true;
/*     */     }
/*     */     
/* 134 */     if (!(obj instanceof KeyedObject)) {
/* 135 */       return false;
/*     */     }
/* 137 */     KeyedObject that = (KeyedObject)obj;
/* 138 */     if (!ObjectUtilities.equal(this.key, that.key)) {
/* 139 */       return false;
/*     */     }
/*     */     
/* 142 */     if (!ObjectUtilities.equal(this.object, that.object)) {
/* 143 */       return false;
/*     */     }
/*     */     
/* 146 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/KeyedObject.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */