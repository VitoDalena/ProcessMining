/*     */ package org.apache.commons.collections15.keyvalue;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiKey<K>
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4465448607415788805L;
/*     */   private final K[] keys;
/*     */   private final int hashCode;
/*     */   
/*     */   public MultiKey(K... keys)
/*     */   {
/*  73 */     this(keys, true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultiKey(K[] keys, boolean makeClone)
/*     */   {
/* 102 */     if (keys == null) {
/* 103 */       throw new IllegalArgumentException("The array of keys must not be null");
/*     */     }
/* 105 */     if (makeClone) {
/* 106 */       this.keys = ((Object[])keys.clone());
/*     */     } else {
/* 108 */       this.keys = keys;
/*     */     }
/*     */     
/* 111 */     int total = 0;
/* 112 */     for (int i = 0; i < keys.length; i++) {
/* 113 */       if (keys[i] != null) {
/* 114 */         total ^= keys[i].hashCode();
/*     */       }
/*     */     }
/* 117 */     this.hashCode = total;
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
/*     */   public K[] getKeys()
/*     */   {
/* 130 */     return (Object[])this.keys.clone();
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
/*     */   public K getKey(int index)
/*     */   {
/* 145 */     return (K)this.keys[index];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 155 */     return this.keys.length;
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
/*     */   public boolean equals(Object other)
/*     */   {
/* 169 */     if (other == this) {
/* 170 */       return true;
/*     */     }
/* 172 */     if ((other instanceof MultiKey)) {
/* 173 */       MultiKey otherMulti = (MultiKey)other;
/* 174 */       return Arrays.equals(this.keys, otherMulti.keys);
/*     */     }
/* 176 */     return false;
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
/*     */   public int hashCode()
/*     */   {
/* 190 */     return this.hashCode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 199 */     return "MultiKey" + Arrays.asList(this.keys).toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/keyvalue/MultiKey.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */