/*     */ package org.apache.commons.collections15.set;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.CollectionUtils;
/*     */ import org.apache.commons.collections15.collection.CompositeCollection;
/*     */ import org.apache.commons.collections15.collection.CompositeCollection.CollectionMutator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompositeSet<E>
/*     */   extends CompositeCollection<E>
/*     */   implements Set<E>
/*     */ {
/*     */   public CompositeSet() {}
/*     */   
/*     */   public CompositeSet(Set<E> set)
/*     */   {
/*  51 */     super(set);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public CompositeSet(Set<E>... sets)
/*     */   {
/*  58 */     super(sets);
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
/*     */   public synchronized void addComposited(Collection<? extends E> c)
/*     */   {
/*  73 */     if (!(c instanceof Set)) {
/*  74 */       throw new IllegalArgumentException("Collections added must implement java.util.Set");
/*     */     }
/*     */     
/*  77 */     for (Iterator i = getCollections().iterator(); i.hasNext();) {
/*  78 */       Set set = (Set)i.next();
/*  79 */       Collection intersects = CollectionUtils.intersection(set, c);
/*  80 */       if (intersects.size() > 0) {
/*  81 */         if (this.mutator == null)
/*  82 */           throw new UnsupportedOperationException("Collision adding composited collection with no SetMutator set");
/*  83 */         if (!(this.mutator instanceof SetMutator)) {
/*  84 */           throw new UnsupportedOperationException("Collision adding composited collection to a CompositeSet with a CollectionMutator instead of a SetMutator");
/*     */         }
/*  86 */         ((SetMutator)this.mutator).resolveCollision(this, set, (Set)c, intersects);
/*  87 */         if (CollectionUtils.intersection(set, c).size() > 0) {
/*  88 */           throw new IllegalArgumentException("Attempt to add illegal entry unresolved by SetMutator.resolveCollision()");
/*     */         }
/*     */       }
/*     */     }
/*  92 */     super.addComposited((Collection[])new Collection[] { c });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public synchronized void addComposited(Collection<? extends E> c, Collection<? extends E> d)
/*     */   {
/* 102 */     if (!(c instanceof Set)) throw new IllegalArgumentException("Argument must implement java.util.Set");
/* 103 */     if (!(d instanceof Set)) throw new IllegalArgumentException("Argument must implement java.util.Set");
/* 104 */     addComposited(new Set[] { (Set)c, (Set)d });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addComposited(Collection<? extends E>... comps)
/*     */   {
/* 114 */     for (int i = comps.length - 1; i >= 0; i--) {
/* 115 */       addComposited(comps[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMutator(CompositeCollection.CollectionMutator<E> mutator)
/*     */   {
/* 127 */     super.setMutator(mutator);
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
/*     */   public boolean remove(Object obj)
/*     */   {
/* 140 */     for (Iterator i = getCollections().iterator(); i.hasNext();) {
/* 141 */       Set set = (Set)i.next();
/* 142 */       if (set.contains(obj)) return set.remove(obj);
/*     */     }
/* 144 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 152 */     if ((obj instanceof Set)) {
/* 153 */       Set set = (Set)obj;
/* 154 */       if ((set.containsAll(this)) && (set.size() == size())) {
/* 155 */         return true;
/*     */       }
/*     */     }
/* 158 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 165 */     int code = 0;
/* 166 */     for (Iterator i = iterator(); i.hasNext();) {
/* 167 */       Object next = i.next();
/* 168 */       code += (next != null ? next.hashCode() : 0);
/*     */     }
/* 170 */     return code;
/*     */   }
/*     */   
/*     */   public static abstract interface SetMutator<E>
/*     */     extends CompositeCollection.CollectionMutator<E>
/*     */   {
/*     */     public abstract void resolveCollision(CompositeSet<E> paramCompositeSet, Set<E> paramSet1, Set<E> paramSet2, Collection<E> paramCollection);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/CompositeSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */