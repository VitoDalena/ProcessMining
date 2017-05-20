/*     */ package org.apache.commons.collections15.collection;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformedCollection<I, O>
/*     */   extends AbstractSerializableCollectionDecorator
/*     */ {
/*     */   private static final long serialVersionUID = 8692300188161871514L;
/*     */   protected final Transformer<? super I, ? extends O> transformer;
/*     */   
/*     */   public static <I, O> Collection<O> decorate(Collection<I> coll, Transformer<? super I, ? extends O> transformer)
/*     */   {
/*  66 */     return new TransformedCollection(coll, transformer);
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
/*     */   protected TransformedCollection(Collection<I> coll, Transformer<? super I, ? extends O> transformer)
/*     */   {
/*  81 */     super(coll);
/*  82 */     if (transformer == null) {
/*  83 */       throw new IllegalArgumentException("Transformer must not be null");
/*     */     }
/*  85 */     this.transformer = transformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected O transform(I object)
/*     */   {
/*  97 */     return (O)this.transformer.transform(object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Collection<O> transform(Collection<? extends I> coll)
/*     */   {
/* 109 */     List<O> list = new ArrayList(coll.size());
/* 110 */     for (Iterator<? extends I> it = coll.iterator(); it.hasNext();) {
/* 111 */       list.add(transform(it.next()));
/*     */     }
/* 113 */     return list;
/*     */   }
/*     */   
/*     */   public boolean add(Object object)
/*     */   {
/* 118 */     O transformed = transform(object);
/* 119 */     return getCollection().add(transformed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean addTyped(I object)
/*     */   {
/* 126 */     return add(object);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 130 */     Collection<O> col2 = transform(coll);
/* 131 */     return getCollection().addAll(col2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean addAllTyped(Collection<? extends I> coll)
/*     */   {
/* 138 */     return addAll(coll);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/collection/TransformedCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */