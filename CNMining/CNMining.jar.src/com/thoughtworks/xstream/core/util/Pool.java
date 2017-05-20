/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Pool
/*    */ {
/*    */   private final int initialPoolSize;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private final int maxPoolSize;
/*    */   
/*    */ 
/*    */ 
/*    */   private final Factory factory;
/*    */   
/*    */ 
/*    */ 
/*    */   private transient Object[] pool;
/*    */   
/*    */ 
/*    */ 
/*    */   private transient int nextAvailable;
/*    */   
/*    */ 
/*    */ 
/* 30 */   private transient Object mutex = new Object();
/*    */   
/*    */   public Pool(int initialPoolSize, int maxPoolSize, Factory factory) {
/* 33 */     this.initialPoolSize = initialPoolSize;
/* 34 */     this.maxPoolSize = maxPoolSize;
/* 35 */     this.factory = factory;
/*    */   }
/*    */   
/*    */   public Object fetchFromPool() {
/*    */     Object result;
/* 40 */     synchronized (this.mutex) {
/* 41 */       if (this.pool == null) {
/* 42 */         this.pool = new Object[this.maxPoolSize];
/* 43 */         for (this.nextAvailable = this.initialPoolSize; this.nextAvailable > 0;) {
/* 44 */           putInPool(this.factory.newInstance());
/*    */         }
/*    */       }
/* 47 */       while (this.nextAvailable == this.maxPoolSize) {
/*    */         try {
/* 49 */           this.mutex.wait();
/*    */         } catch (InterruptedException e) {
/* 51 */           throw new RuntimeException("Interrupted whilst waiting for a free item in the pool : " + e.getMessage());
/*    */         }
/*    */       }
/*    */       
/* 55 */       result = this.pool[(this.nextAvailable++)];
/* 56 */       if (result == null) {
/* 57 */         result = this.factory.newInstance();
/* 58 */         putInPool(result);
/* 59 */         this.nextAvailable += 1;
/*    */       }
/*    */     }
/* 62 */     return result;
/*    */   }
/*    */   
/*    */   protected void putInPool(Object object) {
/* 66 */     synchronized (this.mutex) {
/* 67 */       this.pool[(--this.nextAvailable)] = object;
/* 68 */       this.mutex.notify();
/*    */     }
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 73 */     this.mutex = new Object();
/* 74 */     return this;
/*    */   }
/*    */   
/*    */   public static abstract interface Factory
/*    */   {
/*    */     public abstract Object newInstance();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/Pool.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */