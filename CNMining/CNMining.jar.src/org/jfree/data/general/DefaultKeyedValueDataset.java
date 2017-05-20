/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.DefaultKeyedValue;
/*     */ import org.jfree.data.KeyedValue;
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
/*     */ public class DefaultKeyedValueDataset
/*     */   extends AbstractDataset
/*     */   implements KeyedValueDataset, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8149484339560406750L;
/*     */   private KeyedValue data;
/*     */   
/*     */   public DefaultKeyedValueDataset()
/*     */   {
/*  66 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultKeyedValueDataset(Comparable key, Number value)
/*     */   {
/*  76 */     this(new DefaultKeyedValue(key, value));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultKeyedValueDataset(KeyedValue data)
/*     */   {
/*  86 */     this.data = data;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getKey()
/*     */   {
/*  96 */     Comparable result = null;
/*  97 */     if (this.data != null) {
/*  98 */       result = this.data.getKey();
/*     */     }
/* 100 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getValue()
/*     */   {
/* 109 */     Number result = null;
/* 110 */     if (this.data != null) {
/* 111 */       result = this.data.getValue();
/*     */     }
/* 113 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateValue(Number value)
/*     */   {
/* 122 */     if (this.data == null) {
/* 123 */       throw new RuntimeException("updateValue: can't update null.");
/*     */     }
/* 125 */     setValue(this.data.getKey(), value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(Comparable key, Number value)
/*     */   {
/* 136 */     this.data = new DefaultKeyedValue(key, value);
/* 137 */     notifyListeners(new DatasetChangeEvent(this, this));
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
/* 148 */     if (obj == this) {
/* 149 */       return true;
/*     */     }
/* 151 */     if (!(obj instanceof KeyedValueDataset)) {
/* 152 */       return false;
/*     */     }
/* 154 */     KeyedValueDataset that = (KeyedValueDataset)obj;
/* 155 */     if (this.data == null) {
/* 156 */       if ((that.getKey() != null) || (that.getValue() != null)) {
/* 157 */         return false;
/*     */       }
/* 159 */       return true;
/*     */     }
/* 161 */     if (!ObjectUtilities.equal(this.data.getKey(), that.getKey())) {
/* 162 */       return false;
/*     */     }
/* 164 */     if (!ObjectUtilities.equal(this.data.getValue(), that.getValue())) {
/* 165 */       return false;
/*     */     }
/* 167 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 176 */     return this.data != null ? this.data.hashCode() : 0;
/*     */   }
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
/* 188 */     DefaultKeyedValueDataset clone = (DefaultKeyedValueDataset)super.clone();
/*     */     
/* 190 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/DefaultKeyedValueDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */