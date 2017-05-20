/*     */ package org.jfree.threads;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReaderWriterLock
/*     */ {
/*     */   private ArrayList waiters;
/*     */   
/*     */   private static class ReaderWriterNode
/*     */   {
/*     */     protected static final int READER = 0;
/*     */     protected static final int WRITER = 1;
/*     */     protected Thread t;
/*     */     protected int state;
/*     */     protected int nAcquires;
/*     */     
/*     */     ReaderWriterNode(Thread x0, int x1, ReaderWriterLock.1 x2)
/*     */     {
/*  56 */       this(x0, x1);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private ReaderWriterNode(Thread t, int state)
/*     */     {
/*  80 */       this.t = t;
/*  81 */       this.state = state;
/*  82 */       this.nAcquires = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ReaderWriterLock()
/*     */   {
/*  94 */     this.waiters = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void lockRead()
/*     */   {
/* 102 */     Thread me = Thread.currentThread();
/* 103 */     int index = getIndex(me);
/* 104 */     ReaderWriterNode node; if (index == -1) {
/* 105 */       ReaderWriterNode node = new ReaderWriterNode(me, 0, null);
/* 106 */       this.waiters.add(node);
/*     */     }
/*     */     else {
/* 109 */       node = (ReaderWriterNode)this.waiters.get(index);
/*     */     }
/* 111 */     while (getIndex(me) > firstWriter()) {
/*     */       try {
/* 113 */         wait();
/*     */       }
/*     */       catch (Exception e) {
/* 116 */         System.err.println("ReaderWriterLock.lockRead(): exception.");
/* 117 */         System.err.print(e.getMessage());
/*     */       }
/*     */     }
/* 120 */     node.nAcquires += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void lockWrite()
/*     */   {
/* 128 */     Thread me = Thread.currentThread();
/* 129 */     int index = getIndex(me);
/* 130 */     ReaderWriterNode node; if (index == -1) {
/* 131 */       ReaderWriterNode node = new ReaderWriterNode(me, 1, null);
/* 132 */       this.waiters.add(node);
/*     */     }
/*     */     else {
/* 135 */       node = (ReaderWriterNode)this.waiters.get(index);
/* 136 */       if (node.state == 0) {
/* 137 */         throw new IllegalArgumentException("Upgrade lock");
/*     */       }
/* 139 */       node.state = 1;
/*     */     }
/* 141 */     while (getIndex(me) != 0) {
/*     */       try {
/* 143 */         wait();
/*     */       }
/*     */       catch (Exception e) {
/* 146 */         System.err.println("ReaderWriterLock.lockWrite(): exception.");
/* 147 */         System.err.print(e.getMessage());
/*     */       }
/*     */     }
/* 150 */     node.nAcquires += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void unlock()
/*     */   {
/* 159 */     Thread me = Thread.currentThread();
/* 160 */     int index = getIndex(me);
/* 161 */     if (index > firstWriter()) {
/* 162 */       throw new IllegalArgumentException("Lock not held");
/*     */     }
/* 164 */     ReaderWriterNode node = (ReaderWriterNode)this.waiters.get(index);
/* 165 */     node.nAcquires -= 1;
/* 166 */     if (node.nAcquires == 0) {
/* 167 */       this.waiters.remove(index);
/*     */     }
/* 169 */     notifyAll();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int firstWriter()
/*     */   {
/* 178 */     Iterator e = this.waiters.iterator();
/* 179 */     int index = 0;
/* 180 */     while (e.hasNext()) {
/* 181 */       ReaderWriterNode node = (ReaderWriterNode)e.next();
/* 182 */       if (node.state == 1) {
/* 183 */         return index;
/*     */       }
/* 185 */       index++;
/*     */     }
/* 187 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int getIndex(Thread t)
/*     */   {
/* 198 */     Iterator e = this.waiters.iterator();
/* 199 */     int index = 0;
/* 200 */     while (e.hasNext()) {
/* 201 */       ReaderWriterNode node = (ReaderWriterNode)e.next();
/* 202 */       if (node.t == t) {
/* 203 */         return index;
/*     */       }
/* 205 */       index++;
/*     */     }
/* 207 */     return -1;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/threads/ReaderWriterLock.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */