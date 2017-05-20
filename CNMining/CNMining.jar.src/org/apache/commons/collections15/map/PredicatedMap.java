/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredicatedMap<K, V>
/*     */   extends AbstractInputCheckedMapDecorator<K, V>
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7412622456128415156L;
/*     */   protected final Predicate<? super K> keyPredicate;
/*     */   protected final Predicate<? super V> valuePredicate;
/*     */   
/*     */   public static <K, V> Map<K, V> decorate(Map<K, V> map, Predicate<? super K> keyPredicate, Predicate<? super V> valuePredicate)
/*     */   {
/*  74 */     return new PredicatedMap(map, keyPredicate, valuePredicate);
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
/*     */   protected PredicatedMap(Map<K, V> map, Predicate<? super K> keyPredicate, Predicate<? super V> valuePredicate)
/*     */   {
/*  87 */     super(map);
/*  88 */     this.keyPredicate = keyPredicate;
/*  89 */     this.valuePredicate = valuePredicate;
/*     */     
/*  91 */     Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
/*  92 */     while (it.hasNext()) {
/*  93 */       Map.Entry<K, V> entry = (Map.Entry)it.next();
/*  94 */       K key = entry.getKey();
/*  95 */       V value = entry.getValue();
/*  96 */       validate(key, value);
/*     */     }
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
/* 109 */     out.defaultWriteObject();
/* 110 */     out.writeObject(this.map);
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
/* 122 */     in.defaultReadObject();
/* 123 */     this.map = ((Map)in.readObject());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void validate(K key, V value)
/*     */   {
/* 135 */     if ((this.keyPredicate != null) && (!this.keyPredicate.evaluate(key))) {
/* 136 */       throw new IllegalArgumentException("Cannot add key - Predicate rejected it");
/*     */     }
/* 138 */     if ((this.valuePredicate != null) && (!this.valuePredicate.evaluate(value))) {
/* 139 */       throw new IllegalArgumentException("Cannot add value - Predicate rejected it");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected V checkSetValue(V value)
/*     */   {
/* 151 */     if (!this.valuePredicate.evaluate(value)) {
/* 152 */       throw new IllegalArgumentException("Cannot set value - Predicate rejected it");
/*     */     }
/* 154 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isSetValueChecking()
/*     */   {
/* 164 */     return this.valuePredicate != null;
/*     */   }
/*     */   
/*     */   public V put(K key, V value)
/*     */   {
/* 169 */     validate(key, value);
/* 170 */     return (V)this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> mapToCopy) {
/* 174 */     Iterator it = mapToCopy.entrySet().iterator();
/* 175 */     while (it.hasNext()) {
/* 176 */       Map.Entry entry = (Map.Entry)it.next();
/* 177 */       K key = entry.getKey();
/* 178 */       V value = entry.getValue();
/* 179 */       validate(key, value);
/*     */     }
/* 181 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/PredicatedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */