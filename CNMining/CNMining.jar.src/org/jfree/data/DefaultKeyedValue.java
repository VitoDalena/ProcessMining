/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultKeyedValue
/*     */   implements KeyedValue, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7388924517460437712L;
/*     */   private Comparable key;
/*     */   private Number value;
/*     */   
/*     */   public DefaultKeyedValue(Comparable key, Number value)
/*     */   {
/*  79 */     if (key == null) {
/*  80 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/*  82 */     this.key = key;
/*  83 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getKey()
/*     */   {
/*  92 */     return this.key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getValue()
/*     */   {
/* 101 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setValue(Number value)
/*     */   {
/* 110 */     this.value = value;
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
/* 121 */     if (obj == this) {
/* 122 */       return true;
/*     */     }
/* 124 */     if (!(obj instanceof DefaultKeyedValue)) {
/* 125 */       return false;
/*     */     }
/* 127 */     DefaultKeyedValue that = (DefaultKeyedValue)obj;
/*     */     
/* 129 */     if (!this.key.equals(that.key)) {
/* 130 */       return false;
/*     */     }
/* 132 */     if (this.value != null ? !this.value.equals(that.value) : that.value != null)
/*     */     {
/* 134 */       return false;
/*     */     }
/* 136 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 146 */     int result = this.key != null ? this.key.hashCode() : 0;
/* 147 */     result = 29 * result + (this.value != null ? this.value.hashCode() : 0);
/* 148 */     return result;
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
/* 162 */     DefaultKeyedValue clone = (DefaultKeyedValue)super.clone();
/* 163 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 173 */     return "(" + this.key.toString() + ", " + this.value.toString() + ")";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/DefaultKeyedValue.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */