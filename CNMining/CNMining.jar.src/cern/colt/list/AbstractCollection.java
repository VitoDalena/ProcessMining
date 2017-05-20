/*    */ package cern.colt.list;
/*    */ 
/*    */ import cern.colt.PersistentObject;
/*    */ import java.util.ArrayList;
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
/*    */ 
/*    */ public abstract class AbstractCollection
/*    */   extends PersistentObject
/*    */ {
/*    */   public abstract void clear();
/*    */   
/*    */   public boolean isEmpty()
/*    */   {
/* 41 */     return size() == 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract int size();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract ArrayList toList();
/*    */   
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 58 */     return toList().toString();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/AbstractCollection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */