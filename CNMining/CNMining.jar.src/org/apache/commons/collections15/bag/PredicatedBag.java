/*     */ package org.apache.commons.collections15.bag;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Bag;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ import org.apache.commons.collections15.collection.PredicatedCollection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredicatedBag<E>
/*     */   extends PredicatedCollection<E>
/*     */   implements Bag<E>
/*     */ {
/*     */   private static final long serialVersionUID = -2575833140344736876L;
/*     */   
/*     */   public static <E> Bag<E> decorate(Bag<E> bag, Predicate<? super E> predicate)
/*     */   {
/*  63 */     return new PredicatedBag(bag, predicate);
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
/*     */   protected PredicatedBag(Bag<E> bag, Predicate<? super E> predicate)
/*     */   {
/*  79 */     super(bag, predicate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Bag<E> getBag()
/*     */   {
/*  88 */     return (Bag)getCollection();
/*     */   }
/*     */   
/*     */   public boolean add(E object, int count)
/*     */   {
/*  93 */     validate(object);
/*  94 */     return getBag().add(object, count);
/*     */   }
/*     */   
/*     */   public boolean remove(E object, int count) {
/*  98 */     return getBag().remove(object, count);
/*     */   }
/*     */   
/*     */   public Set uniqueSet() {
/* 102 */     return getBag().uniqueSet();
/*     */   }
/*     */   
/*     */   public int getCount(E object) {
/* 106 */     return getBag().getCount(object);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/PredicatedBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */