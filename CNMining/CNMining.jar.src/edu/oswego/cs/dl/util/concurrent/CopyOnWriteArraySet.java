/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CopyOnWriteArraySet
/*     */   extends AbstractSet
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   protected final CopyOnWriteArrayList al;
/*     */   
/*     */   public CopyOnWriteArraySet()
/*     */   {
/*  75 */     this.al = new CopyOnWriteArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public CopyOnWriteArraySet(Collection paramCollection)
/*     */   {
/*  83 */     this.al = new CopyOnWriteArrayList();
/*  84 */     this.al.addAllAbsent(paramCollection);
/*     */   }
/*     */   
/*     */ 
/*  88 */   public int size() { return this.al.size(); }
/*  89 */   public boolean isEmpty() { return this.al.isEmpty(); }
/*  90 */   public boolean contains(Object paramObject) { return this.al.contains(paramObject); }
/*  91 */   public Object[] toArray() { return this.al.toArray(); }
/*  92 */   public Object[] toArray(Object[] paramArrayOfObject) { return this.al.toArray(paramArrayOfObject); }
/*  93 */   public void clear() { this.al.clear(); }
/*  94 */   public Iterator iterator() { return this.al.iterator(); }
/*  95 */   public boolean remove(Object paramObject) { return this.al.remove(paramObject); }
/*  96 */   public boolean containsAll(Collection paramCollection) { return this.al.containsAll(paramCollection); }
/*  97 */   public boolean addAll(Collection paramCollection) { return this.al.addAllAbsent(paramCollection) > 0; }
/*  98 */   public boolean removeAll(Collection paramCollection) { return this.al.removeAll(paramCollection); }
/*  99 */   public boolean retainAll(Collection paramCollection) { return this.al.retainAll(paramCollection); }
/* 100 */   public boolean add(Object paramObject) { return this.al.addIfAbsent(paramObject); }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/CopyOnWriteArraySet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */