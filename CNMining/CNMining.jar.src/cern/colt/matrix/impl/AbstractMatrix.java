/*    */ package cern.colt.matrix.impl;
/*    */ 
/*    */ import cern.colt.PersistentObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractMatrix
/*    */   extends PersistentObject
/*    */ {
/* 21 */   protected boolean isNoView = true;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void ensureCapacity(int paramInt) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected boolean isView()
/*    */   {
/* 40 */     return !this.isNoView;
/*    */   }
/*    */   
/*    */   public abstract int size();
/*    */   
/*    */   public void trimToSize() {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/AbstractMatrix.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */