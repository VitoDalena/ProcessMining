/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections15.ArrayStack;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectGraphIterator
/*     */   implements Iterator
/*     */ {
/*  85 */   protected final ArrayStack stack = new ArrayStack(8);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Object root;
/*     */   
/*     */ 
/*     */ 
/*     */   protected Transformer transformer;
/*     */   
/*     */ 
/*     */ 
/*  98 */   protected boolean hasNext = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Iterator currentIterator;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Object currentValue;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Iterator lastUsedIterator;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectGraphIterator(Object root, Transformer transformer)
/*     */   {
/* 124 */     if ((root instanceof Iterator)) {
/* 125 */       this.currentIterator = ((Iterator)root);
/*     */     } else {
/* 127 */       this.root = root;
/*     */     }
/* 129 */     this.transformer = transformer;
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
/*     */   public ObjectGraphIterator(Iterator rootIterator)
/*     */   {
/* 144 */     this.currentIterator = rootIterator;
/* 145 */     this.transformer = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void updateCurrentIterator()
/*     */   {
/* 153 */     if (this.hasNext) {
/* 154 */       return;
/*     */     }
/* 156 */     if (this.currentIterator == null) {
/* 157 */       if (this.root != null)
/*     */       {
/*     */ 
/* 160 */         if (this.transformer == null) {
/* 161 */           findNext(this.root);
/*     */         } else {
/* 163 */           findNext(this.transformer.transform(this.root));
/*     */         }
/* 165 */         this.root = null;
/*     */       }
/*     */     } else {
/* 168 */       findNextByIterator(this.currentIterator);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void findNext(Object value)
/*     */   {
/* 178 */     if ((value instanceof Iterator))
/*     */     {
/* 180 */       findNextByIterator((Iterator)value);
/*     */     }
/*     */     else {
/* 183 */       this.currentValue = value;
/* 184 */       this.hasNext = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void findNextByIterator(Iterator iterator)
/*     */   {
/* 194 */     if (iterator != this.currentIterator)
/*     */     {
/* 196 */       if (this.currentIterator != null) {
/* 197 */         this.stack.push(this.currentIterator);
/*     */       }
/* 199 */       this.currentIterator = iterator;
/*     */     }
/*     */     
/* 202 */     while ((this.currentIterator.hasNext()) && (!this.hasNext)) {
/* 203 */       Object next = this.currentIterator.next();
/* 204 */       if (this.transformer != null) {
/* 205 */         next = this.transformer.transform(next);
/*     */       }
/* 207 */       findNext(next);
/*     */     }
/* 209 */     if (!this.hasNext)
/*     */     {
/* 211 */       if (!this.stack.isEmpty())
/*     */       {
/*     */ 
/*     */ 
/* 215 */         this.currentIterator = ((Iterator)this.stack.pop());
/* 216 */         findNextByIterator(this.currentIterator);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 227 */     updateCurrentIterator();
/* 228 */     return this.hasNext;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object next()
/*     */   {
/* 238 */     updateCurrentIterator();
/* 239 */     if (!this.hasNext) {
/* 240 */       throw new NoSuchElementException("No more elements in the iteration");
/*     */     }
/* 242 */     this.lastUsedIterator = this.currentIterator;
/* 243 */     Object result = this.currentValue;
/* 244 */     this.currentValue = null;
/* 245 */     this.hasNext = false;
/* 246 */     return result;
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
/*     */   public void remove()
/*     */   {
/* 261 */     if (this.lastUsedIterator == null) {
/* 262 */       throw new IllegalStateException("Iterator remove() cannot be called at this time");
/*     */     }
/* 264 */     this.lastUsedIterator.remove();
/* 265 */     this.lastUsedIterator = null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/ObjectGraphIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */