/*    */ package edu.oswego.cs.dl.util.concurrent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LinkedNode
/*    */ {
/*    */   public Object value;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public LinkedNode next;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public LinkedNode() {}
/*    */   
/*    */ 
/*    */ 
/* 23 */   public LinkedNode(Object paramObject) { this.value = paramObject; }
/* 24 */   public LinkedNode(Object paramObject, LinkedNode paramLinkedNode) { this.value = paramObject;this.next = paramLinkedNode;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/LinkedNode.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */