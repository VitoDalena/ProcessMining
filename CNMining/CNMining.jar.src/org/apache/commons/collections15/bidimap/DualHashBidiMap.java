/*     */ package org.apache.commons.collections15.bidimap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.BidiMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DualHashBidiMap<K, V>
/*     */   extends AbstractDualBidiMap<K, V>
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 721969328361808L;
/*     */   
/*     */   public DualHashBidiMap()
/*     */   {
/*  55 */     super(new HashMap(), new HashMap());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DualHashBidiMap(Map<? extends K, ? extends V> map)
/*     */   {
/*  65 */     super(new HashMap(), new HashMap());
/*  66 */     putAll(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DualHashBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap, BidiMap<V, K> inverseBidiMap)
/*     */   {
/*  77 */     super(normalMap, reverseMap, inverseBidiMap);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected <K, V> BidiMap<K, V> createBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap, BidiMap<V, K> inverseBidiMap)
/*     */   {
/*  89 */     return new DualHashBidiMap(normalMap, reverseMap, inverseBidiMap);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/*  95 */     out.defaultWriteObject();
/*  96 */     out.writeObject(this.forwardMap);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 100 */     in.defaultReadObject();
/* 101 */     this.forwardMap = new HashMap();
/* 102 */     this.inverseMap = new HashMap();
/* 103 */     Map<K, V> map = (Map)in.readObject();
/* 104 */     putAll(map);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bidimap/DualHashBidiMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */