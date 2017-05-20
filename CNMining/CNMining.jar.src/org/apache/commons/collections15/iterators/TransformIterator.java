/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformIterator<I, O>
/*     */   implements Iterator<O>
/*     */ {
/*     */   private Iterator<I> iterator;
/*     */   private Transformer<I, O> transformer;
/*     */   
/*     */   public TransformIterator() {}
/*     */   
/*     */   public TransformIterator(Iterator<I> iterator)
/*     */   {
/*  60 */     this.iterator = iterator;
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
/*     */   public TransformIterator(Iterator<I> iterator, Transformer<I, O> transformer)
/*     */   {
/*  73 */     this.iterator = iterator;
/*  74 */     this.transformer = transformer;
/*     */   }
/*     */   
/*     */   public boolean hasNext()
/*     */   {
/*  79 */     return this.iterator.hasNext();
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
/*     */   public O next()
/*     */   {
/*  92 */     return (O)transform(this.iterator.next());
/*     */   }
/*     */   
/*     */   public void remove() {
/*  96 */     this.iterator.remove();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<I> getIterator()
/*     */   {
/* 106 */     return this.iterator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIterator(Iterator<I> iterator)
/*     */   {
/* 116 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer<I, O> getTransformer()
/*     */   {
/* 126 */     return this.transformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTransformer(Transformer<I, O> transformer)
/*     */   {
/* 136 */     this.transformer = transformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected O transform(I source)
/*     */   {
/* 148 */     if (this.transformer != null) {
/* 149 */       return (O)this.transformer.transform(source);
/*     */     }
/*     */     
/* 152 */     Object sourceObj = source;
/* 153 */     return (O)sourceObj;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/TransformIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */