/*     */ package org.apache.commons.collections15.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.collection.TransformedCollection;
/*     */ import org.apache.commons.collections15.iterators.AbstractListIteratorDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformedList<I, O>
/*     */   extends TransformedCollection<I, O>
/*     */   implements List
/*     */ {
/*     */   private static final long serialVersionUID = 1077193035000013141L;
/*     */   
/*     */   public static <I, O> List<O> decorate(List<I> list, Transformer<? super I, ? extends O> transformer)
/*     */   {
/*  61 */     return new TransformedList(list, transformer);
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
/*     */   protected TransformedList(List<I> list, Transformer<? super I, ? extends O> transformer)
/*     */   {
/*  76 */     super(list, transformer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected List<O> getList()
/*     */   {
/*  85 */     return (List)this.collection;
/*     */   }
/*     */   
/*     */   public O get(int index)
/*     */   {
/*  90 */     return (O)getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  94 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/*  98 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 102 */     return getList().remove(index);
/*     */   }
/*     */   
/*     */   public void add(int index, Object object)
/*     */   {
/* 107 */     O transformed = transform(object);
/* 108 */     getList().add(index, transformed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addTyped(int index, I object)
/*     */   {
/* 115 */     add(index, object);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 119 */     Collection<O> transformed = transform(coll);
/* 120 */     return getList().addAll(index, transformed);
/*     */   }
/*     */   
/*     */   public ListIterator<O> listIterator() {
/* 124 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public ListIterator<O> listIterator(int i) {
/* 128 */     return new TransformedListIterator(getList().listIterator(i));
/*     */   }
/*     */   
/*     */   public O set(int index, Object object) {
/* 132 */     O transformed = transform(object);
/* 133 */     return (O)getList().set(index, transformed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public O setTyped(int index, I object)
/*     */   {
/* 140 */     return (O)set(index, object);
/*     */   }
/*     */   
/*     */   public List<O> subList(int fromIndex, int toIndex) {
/* 144 */     List sub = getList().subList(fromIndex, toIndex);
/* 145 */     return new TransformedList(sub, this.transformer);
/*     */   }
/*     */   
/*     */ 
/*     */   protected class TransformedListIterator
/*     */     extends AbstractListIteratorDecorator
/*     */   {
/*     */     protected TransformedListIterator()
/*     */     {
/* 154 */       super();
/*     */     }
/*     */     
/*     */     public void add(Object object) {
/* 158 */       O transformed = TransformedList.this.transform(object);
/* 159 */       this.iterator.add(transformed);
/*     */     }
/*     */     
/*     */     public void set(Object object) {
/* 163 */       O transformed = TransformedList.this.transform(object);
/* 164 */       this.iterator.set(transformed);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/TransformedList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */