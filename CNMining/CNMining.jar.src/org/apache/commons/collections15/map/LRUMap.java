/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.BoundedMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LRUMap<K, V>
/*     */   extends AbstractLinkedMap<K, V>
/*     */   implements BoundedMap<K, V>, Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = -612114643488955218L;
/*     */   protected static final int DEFAULT_MAX_SIZE = 100;
/*     */   private transient int maxSize;
/*     */   private boolean scanUntilRemovable;
/*     */   
/*     */   public LRUMap()
/*     */   {
/*  76 */     this(100, 0.75F, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LRUMap(int maxSize)
/*     */   {
/*  86 */     this(maxSize, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LRUMap(int maxSize, boolean scanUntilRemovable)
/*     */   {
/*  98 */     this(maxSize, 0.75F, scanUntilRemovable);
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
/*     */   public LRUMap(int maxSize, float loadFactor)
/*     */   {
/* 111 */     this(maxSize, loadFactor, false);
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
/*     */   public LRUMap(int maxSize, float loadFactor, boolean scanUntilRemovable)
/*     */   {
/* 126 */     super(maxSize < 1 ? 16 : maxSize, loadFactor);
/* 127 */     if (maxSize < 1) {
/* 128 */       throw new IllegalArgumentException("LRUMap max size must be greater than 0");
/*     */     }
/* 130 */     this.maxSize = maxSize;
/* 131 */     this.scanUntilRemovable = scanUntilRemovable;
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
/*     */   public LRUMap(Map<? extends K, ? extends V> map)
/*     */   {
/* 144 */     this(map, false);
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
/*     */   public LRUMap(Map<? extends K, ? extends V> map, boolean scanUntilRemovable)
/*     */   {
/* 159 */     this(map.size(), 0.75F, scanUntilRemovable);
/* 160 */     putAll(map);
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
/*     */   public V get(Object key)
/*     */   {
/* 174 */     AbstractLinkedMap.LinkEntry<K, V> entry = (AbstractLinkedMap.LinkEntry)getEntry(key);
/* 175 */     if (entry == null) {
/* 176 */       return null;
/*     */     }
/* 178 */     moveToMRU(entry);
/* 179 */     return (V)entry.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void moveToMRU(AbstractLinkedMap.LinkEntry<K, V> entry)
/*     */   {
/* 191 */     if (entry.after != this.header) {
/* 192 */       this.modCount += 1;
/*     */       
/* 194 */       entry.before.after = entry.after;
/* 195 */       entry.after.before = entry.before;
/*     */       
/* 197 */       entry.after = this.header;
/* 198 */       entry.before = this.header.before;
/* 199 */       this.header.before.after = entry;
/* 200 */       this.header.before = entry;
/*     */     }
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
/*     */   protected void updateEntry(AbstractHashedMap.HashEntry<K, V> entry, V newValue)
/*     */   {
/* 214 */     moveToMRU((AbstractLinkedMap.LinkEntry)entry);
/* 215 */     entry.setValue(newValue);
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
/*     */   protected void addMapping(int hashIndex, int hashCode, K key, V value)
/*     */   {
/* 234 */     if (isFull()) {
/* 235 */       AbstractLinkedMap.LinkEntry reuse = this.header.after;
/* 236 */       boolean removeLRUEntry = false;
/* 237 */       if (this.scanUntilRemovable) {
/* 238 */         while (reuse != this.header) {
/* 239 */           if (removeLRU(reuse)) {
/* 240 */             removeLRUEntry = true;
/* 241 */             break;
/*     */           }
/* 243 */           reuse = reuse.after;
/*     */         }
/*     */       }
/* 246 */       removeLRUEntry = removeLRU(reuse);
/*     */       
/*     */ 
/* 249 */       if (removeLRUEntry) {
/* 250 */         reuseMapping(reuse, hashIndex, hashCode, key, value);
/*     */       } else {
/* 252 */         super.addMapping(hashIndex, hashCode, key, value);
/*     */       }
/*     */     } else {
/* 255 */       super.addMapping(hashIndex, hashCode, key, value);
/*     */     }
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
/*     */   protected void reuseMapping(AbstractLinkedMap.LinkEntry<K, V> entry, int hashIndex, int hashCode, K key, V value)
/*     */   {
/* 274 */     int removeIndex = hashIndex(entry.hashCode, this.data.length);
/* 275 */     AbstractHashedMap.HashEntry<K, V> loop = this.data[removeIndex];
/* 276 */     AbstractHashedMap.HashEntry<K, V> previous = null;
/* 277 */     while (loop != entry) {
/* 278 */       previous = loop;
/* 279 */       loop = loop.next;
/*     */     }
/*     */     
/*     */ 
/* 283 */     this.modCount += 1;
/* 284 */     removeEntry(entry, removeIndex, previous);
/* 285 */     reuseEntry(entry, hashIndex, hashCode, key, value);
/* 286 */     addEntry(entry, hashIndex);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean removeLRU(AbstractLinkedMap.LinkEntry<K, V> entry)
/*     */   {
/* 323 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isFull()
/*     */   {
/* 333 */     return this.size >= this.maxSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int maxSize()
/*     */   {
/* 342 */     return this.maxSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isScanUntilRemovable()
/*     */   {
/* 353 */     return this.scanUntilRemovable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 363 */     return super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 370 */     out.defaultWriteObject();
/* 371 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 378 */     in.defaultReadObject();
/* 379 */     doReadObject(in);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void doWriteObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 386 */     out.writeInt(this.maxSize);
/* 387 */     super.doWriteObject(out);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void doReadObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 394 */     this.maxSize = in.readInt();
/* 395 */     super.doReadObject(in);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/LRUMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */