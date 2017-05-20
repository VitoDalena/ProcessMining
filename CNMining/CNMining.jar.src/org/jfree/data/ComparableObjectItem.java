/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComparableObjectItem
/*     */   implements Cloneable, Comparable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2751513470325494890L;
/*     */   private Comparable x;
/*     */   private Object obj;
/*     */   
/*     */   public ComparableObjectItem(Comparable x, Object y)
/*     */   {
/*  72 */     if (x == null) {
/*  73 */       throw new IllegalArgumentException("Null 'x' argument.");
/*     */     }
/*  75 */     this.x = x;
/*  76 */     this.obj = y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Comparable getComparable()
/*     */   {
/*  85 */     return this.x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Object getObject()
/*     */   {
/*  94 */     return this.obj;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setObject(Object y)
/*     */   {
/* 104 */     this.obj = y;
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
/*     */   public int compareTo(Object o1)
/*     */   {
/* 125 */     if ((o1 instanceof ComparableObjectItem)) {
/* 126 */       ComparableObjectItem that = (ComparableObjectItem)o1;
/* 127 */       return this.x.compareTo(that.x);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 134 */     int result = 1;
/*     */     
/*     */ 
/* 137 */     return result;
/*     */   }
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
/* 150 */     return super.clone();
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
/* 162 */     if (obj == this) {
/* 163 */       return true;
/*     */     }
/* 165 */     if (!(obj instanceof ComparableObjectItem)) {
/* 166 */       return false;
/*     */     }
/* 168 */     ComparableObjectItem that = (ComparableObjectItem)obj;
/* 169 */     if (!this.x.equals(that.x)) {
/* 170 */       return false;
/*     */     }
/* 172 */     if (!ObjectUtilities.equal(this.obj, that.obj)) {
/* 173 */       return false;
/*     */     }
/* 175 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 185 */     int result = this.x.hashCode();
/* 186 */     result = 29 * result + (this.obj != null ? this.obj.hashCode() : 0);
/* 187 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/ComparableObjectItem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */