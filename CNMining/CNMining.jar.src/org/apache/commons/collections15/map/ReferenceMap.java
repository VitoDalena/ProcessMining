/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReferenceMap<K, V>
/*     */   extends AbstractReferenceMap<K, V>
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1555089888138299607L;
/*     */   
/*     */   public ReferenceMap()
/*     */   {
/*  80 */     super(0, 1, 16, 0.75F, false);
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
/*     */   public ReferenceMap(int keyType, int valueType)
/*     */   {
/*  93 */     super(keyType, valueType, 16, 0.75F, false);
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
/*     */   public ReferenceMap(int keyType, int valueType, boolean purgeValues)
/*     */   {
/* 108 */     super(keyType, valueType, 16, 0.75F, purgeValues);
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
/*     */   public ReferenceMap(int keyType, int valueType, int capacity, float loadFactor)
/*     */   {
/* 124 */     super(keyType, valueType, capacity, loadFactor, false);
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
/*     */   public ReferenceMap(int keyType, int valueType, int capacity, float loadFactor, boolean purgeValues)
/*     */   {
/* 142 */     super(keyType, valueType, capacity, loadFactor, purgeValues);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 150 */     out.defaultWriteObject();
/* 151 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 158 */     in.defaultReadObject();
/* 159 */     doReadObject(in);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/ReferenceMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */