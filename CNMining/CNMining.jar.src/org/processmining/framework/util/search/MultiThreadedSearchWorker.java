/*     */ package org.processmining.framework.util.search;
/*     */ 
/*     */ import java.rmi.server.UID;
/*     */ import java.util.Collection;
/*     */ import javax.swing.SwingWorker;
/*     */ import org.processmining.framework.plugin.Progress;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MultiThreadedSearchWorker<N>
/*     */   extends SwingWorker<Object, Void>
/*     */ {
/*     */   private final MultiThreadedSearcher<N> owner;
/*     */   private final Progress progress;
/*     */   private final Collection<N> resultCollection;
/*     */   private final ExpandCollection<N> stack;
/* 264 */   private final UID id = new UID();
/*     */   
/*     */   public MultiThreadedSearchWorker(MultiThreadedSearcher<N> owner, Progress progress, Collection<N> resultCollection) {
/* 267 */     this.owner = owner;
/* 268 */     this.progress = progress;
/* 269 */     this.resultCollection = resultCollection;
/* 270 */     this.stack = owner.getStack();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 274 */     return this.id.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o)
/*     */   {
/* 279 */     if ((o instanceof MultiThreadedSearchWorker)) {
/* 280 */       return this.id.equals(((MultiThreadedSearchWorker)o).id);
/*     */     }
/* 282 */     return false;
/*     */   }
/*     */   
/*     */   protected Object doInBackground() throws Exception
/*     */   {
/* 287 */     while (!this.progress.isCancelled())
/*     */     {
/* 289 */       N toExpand = getNodeToExpand(this.progress);
/* 290 */       if (toExpand == null)
/*     */       {
/*     */ 
/* 293 */         synchronized (this.stack) {
/* 294 */           this.stack.notifyAll();
/*     */         }
/* 296 */         break;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 302 */       Collection<N> expandFurther = this.owner.getExpander().expandNode(toExpand, this.progress, this.resultCollection);
/*     */       
/*     */ 
/*     */ 
/* 306 */       processNewNodes(toExpand, expandFurther, this.resultCollection, this.progress);
/*     */     }
/*     */     
/* 309 */     return null;
/*     */   }
/*     */   
/*     */   private N getNodeToExpand(Progress progress) throws InterruptedException {
/* 313 */     synchronized (this.stack) {
/* 314 */       while (!progress.isCancelled()) {
/* 315 */         if (this.stack.isEmpty())
/*     */         {
/* 317 */           if (this.owner.setWaiting(this, Boolean.valueOf(true)))
/*     */           {
/*     */ 
/*     */ 
/* 321 */             return null;
/*     */           }
/*     */           
/* 324 */           this.stack.wait();
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 329 */           this.owner.setWaiting(this, Boolean.valueOf(false));
/*     */           
/* 331 */           N toExpand = this.stack.pop();
/* 332 */           this.stack.notifyAll();
/* 333 */           return toExpand;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 338 */     this.owner.setWaiting(this, Boolean.valueOf(true));
/* 339 */     return null;
/*     */   }
/*     */   
/*     */   private void processNewNodes(N toExpand, Collection<N> expandFurther, Collection<N> resultCollection, Progress progress)
/*     */   {
/* 344 */     synchronized (this.stack) {
/* 345 */       if (!expandFurther.isEmpty())
/*     */       {
/* 347 */         this.stack.add(expandFurther);
/*     */       } else {
/* 349 */         synchronized (resultCollection) {
/* 350 */           this.owner.getExpander().processLeaf(toExpand, progress, resultCollection);
/*     */         }
/*     */       }
/*     */       
/* 354 */       this.stack.notifyAll();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/framework/util/search/MultiThreadedSearchWorker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */