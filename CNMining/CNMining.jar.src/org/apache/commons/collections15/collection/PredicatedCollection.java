/*     */ package org.apache.commons.collections15.collection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
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
/*     */ 
/*     */ public class PredicatedCollection<E>
/*     */   extends AbstractSerializableCollectionDecorator<E>
/*     */ {
/*     */   private static final long serialVersionUID = -5259182142076705162L;
/*     */   protected final Predicate<? super E> predicate;
/*     */   
/*     */   public static <E> Collection<E> decorate(Collection<E> coll, Predicate<? super E> predicate)
/*     */   {
/*  67 */     return new PredicatedCollection(coll, predicate);
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
/*     */   protected PredicatedCollection(Collection<E> coll, Predicate<? super E> predicate)
/*     */   {
/*  83 */     super(coll);
/*  84 */     if (predicate == null) {
/*  85 */       throw new IllegalArgumentException("Predicate must not be null");
/*     */     }
/*  87 */     this.predicate = predicate;
/*  88 */     for (Iterator<E> it = coll.iterator(); it.hasNext();) {
/*  89 */       validate(it.next());
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
/*     */   protected void validate(E object)
/*     */   {
/* 103 */     if (!this.predicate.evaluate(object)) {
/* 104 */       throw new IllegalArgumentException("Cannot add Object '" + object + "' - Predicate rejected it");
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
/*     */   public boolean add(E object)
/*     */   {
/* 118 */     validate(object);
/* 119 */     return getCollection().add(object);
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
/*     */   public boolean addAll(Collection<? extends E> coll)
/*     */   {
/* 132 */     for (Iterator<? extends E> it = coll.iterator(); it.hasNext();) {
/* 133 */       validate(it.next());
/*     */     }
/* 135 */     return getCollection().addAll(coll);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/collection/PredicatedCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */