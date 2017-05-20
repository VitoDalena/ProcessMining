/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableIterator;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableListIterator;
/*     */ import org.apache.commons.collections15.list.UnmodifiableList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LinkedMap<K, V>
/*     */   extends AbstractLinkedMap<K, V>
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 9077234323521161066L;
/*     */   
/*     */   public LinkedMap()
/*     */   {
/*  64 */     super(16, 0.75F, 12);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LinkedMap(int initialCapacity)
/*     */   {
/*  74 */     super(initialCapacity);
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
/*     */   public LinkedMap(int initialCapacity, float loadFactor)
/*     */   {
/*  87 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LinkedMap(Map<? extends K, ? extends V> map)
/*     */   {
/*  97 */     super(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 107 */     return super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 114 */     out.defaultWriteObject();
/* 115 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 122 */     in.defaultReadObject();
/* 123 */     doReadObject(in);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K get(int index)
/*     */   {
/* 135 */     return (K)getEntry(index).getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getValue(int index)
/*     */   {
/* 146 */     return (V)getEntry(index).getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(Object key)
/*     */   {
/* 156 */     int i = 0;
/* 157 */     for (AbstractLinkedMap.LinkEntry entry = this.header.after; entry != this.header; i++) {
/* 158 */       if (isEqualKey(key, entry.getKey())) {
/* 159 */         return i;
/*     */       }
/* 157 */       entry = entry.after;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 162 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V remove(int index)
/*     */   {
/* 174 */     return (V)remove(get(index));
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
/*     */   public List<K> asList()
/*     */   {
/* 193 */     return new LinkedMapList(this);
/*     */   }
/*     */   
/*     */ 
/*     */   static class LinkedMapList<K, V>
/*     */     extends AbstractList<K>
/*     */   {
/*     */     final LinkedMap<K, V> parent;
/*     */     
/*     */     LinkedMapList(LinkedMap<K, V> parent)
/*     */     {
/* 204 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 208 */       return this.parent.size();
/*     */     }
/*     */     
/*     */     public K get(int index) {
/* 212 */       return (K)this.parent.get(index);
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 216 */       return this.parent.containsKey(obj);
/*     */     }
/*     */     
/*     */     public int indexOf(Object obj) {
/* 220 */       return this.parent.indexOf(obj);
/*     */     }
/*     */     
/*     */     public int lastIndexOf(Object obj) {
/* 224 */       return this.parent.indexOf(obj);
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection<?> coll) {
/* 228 */       return this.parent.keySet().containsAll(coll);
/*     */     }
/*     */     
/*     */     public K remove(int index) {
/* 232 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 236 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection<?> coll) {
/* 240 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection<?> coll) {
/* 244 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 248 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 252 */       return this.parent.keySet().toArray();
/*     */     }
/*     */     
/*     */     public <T> T[] toArray(T[] array) {
/* 256 */       return this.parent.keySet().toArray(array);
/*     */     }
/*     */     
/*     */     public Iterator<K> iterator() {
/* 260 */       return UnmodifiableIterator.decorate(this.parent.keySet().iterator());
/*     */     }
/*     */     
/*     */     public ListIterator<K> listIterator() {
/* 264 */       return UnmodifiableListIterator.decorate(super.listIterator());
/*     */     }
/*     */     
/*     */     public ListIterator<K> listIterator(int fromIndex) {
/* 268 */       return UnmodifiableListIterator.decorate(super.listIterator(fromIndex));
/*     */     }
/*     */     
/*     */     public List<K> subList(int fromIndexInclusive, int toIndexExclusive) {
/* 272 */       return UnmodifiableList.decorate(super.subList(fromIndexInclusive, toIndexExclusive));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/LinkedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */