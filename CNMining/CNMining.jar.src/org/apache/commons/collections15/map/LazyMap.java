/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.functors.FactoryTransformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LazyMap<K, V>
/*     */   extends AbstractMapDecorator<K, V>
/*     */   implements Map<K, V>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7990956402564206740L;
/*     */   protected final Transformer<K, V> transformer;
/*     */   
/*     */   public static <K, V> Map<K, V> decorate(Map<K, V> map, Factory<V> factory)
/*     */   {
/*  83 */     return new LazyMap(map, factory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <K, V> Map<K, V> decorate(Map<K, V> map, Transformer<K, V> transformer)
/*     */   {
/*  94 */     return new LazyMap(map, transformer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected LazyMap(Map<K, V> map, Factory<V> factory)
/*     */   {
/* 106 */     super(map);
/* 107 */     if (factory == null) {
/* 108 */       throw new IllegalArgumentException("Factory must not be null");
/*     */     }
/* 110 */     this.transformer = new FactoryTransformer(factory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected LazyMap(Map<K, V> map, Transformer<K, V> transformer)
/*     */   {
/* 121 */     super(map);
/* 122 */     if (transformer == null) {
/* 123 */       throw new IllegalArgumentException("Transformer must not be null");
/*     */     }
/* 125 */     this.transformer = transformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 137 */     out.defaultWriteObject();
/* 138 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 150 */     in.defaultReadObject();
/* 151 */     this.map = ((Map)in.readObject());
/*     */   }
/*     */   
/*     */ 
/*     */   public V get(Object key)
/*     */   {
/* 157 */     if (!this.map.containsKey(key)) {
/* 158 */       V value = this.transformer.transform(key);
/* 159 */       this.map.put(key, value);
/* 160 */       return value;
/*     */     }
/* 162 */     return (V)this.map.get(key);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/LazyMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */