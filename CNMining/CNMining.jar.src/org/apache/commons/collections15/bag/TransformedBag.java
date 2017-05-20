/*     */ package org.apache.commons.collections15.bag;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Bag;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.collection.TransformedCollection;
/*     */ import org.apache.commons.collections15.set.TransformedSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformedBag
/*     */   extends TransformedCollection
/*     */   implements Bag
/*     */ {
/*     */   private static final long serialVersionUID = 5421170911299074185L;
/*     */   
/*     */   public static <I, O> Bag<O> decorate(Bag<I> bag, Transformer<? super I, ? extends O> transformer)
/*     */   {
/*  61 */     return new TransformedBag(bag, transformer);
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
/*     */   protected TransformedBag(Bag bag, Transformer transformer)
/*     */   {
/*  76 */     super(bag, transformer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Bag getBag()
/*     */   {
/*  85 */     return (Bag)this.collection;
/*     */   }
/*     */   
/*     */   public int getCount(Object object)
/*     */   {
/*  90 */     return getBag().getCount(object);
/*     */   }
/*     */   
/*     */   public boolean remove(Object object, int nCopies) {
/*  94 */     return getBag().remove(object, nCopies);
/*     */   }
/*     */   
/*     */   public boolean add(Object object, int nCopies)
/*     */   {
/*  99 */     object = transform(object);
/* 100 */     return getBag().add(object, nCopies);
/*     */   }
/*     */   
/*     */   public Set uniqueSet() {
/* 104 */     Set set = getBag().uniqueSet();
/* 105 */     return TransformedSet.decorate(set, this.transformer);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/TransformedBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */