/*    */ package org.apache.commons.collections15.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections15.Closure;
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
/*    */ 
/*    */ 
/*    */ public class NOPClosure<T>
/*    */   implements Closure<T>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = 3518477308466486130L;
/* 40 */   public static final Closure INSTANCE = new NOPClosure();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <T> Closure<T> getInstance()
/*    */   {
/* 49 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public void execute(T input) {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/NOPClosure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */