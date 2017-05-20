/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.NoSuchElementException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class AbstractIterator<E>
/*    */   implements Iterator<E>
/*    */ {
/*    */   private static final int NOT_CACHED = 0;
/*    */   private static final int CACHED = 1;
/*    */   private static final int AT_END = 2;
/* 17 */   private int state = 0;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private E nextElement;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean hasNext()
/*    */   {
/* 31 */     if (this.state == 0)
/*    */     {
/* 33 */       this.state = 1;
/* 34 */       this.nextElement = fetch();
/*    */     }
/* 36 */     return this.state == 1;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public E next()
/*    */   {
/* 45 */     if (!hasNext()) {
/* 46 */       throw new NoSuchElementException();
/*    */     }
/* 48 */     this.state = 0;
/* 49 */     return (E)this.nextElement;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void remove()
/*    */   {
/* 58 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected abstract E fetch();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected final E done()
/*    */   {
/* 73 */     this.state = 2;
/* 74 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/AbstractIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */