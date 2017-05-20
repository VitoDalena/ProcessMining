/*    */ package cern.colt;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public abstract class PersistentObject
/*    */   implements Serializable, Cloneable
/*    */ {
/*    */   public static final long serialVersionUID = 1020L;
/*    */   
/*    */   public Object clone()
/*    */   {
/*    */     try
/*    */     {
/* 32 */       return super.clone();
/*    */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 34 */       throw new InternalError();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/PersistentObject.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */