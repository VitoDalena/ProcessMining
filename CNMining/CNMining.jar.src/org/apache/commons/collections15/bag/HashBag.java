/*    */ package org.apache.commons.collections15.bag;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import org.apache.commons.collections15.Bag;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HashBag<E>
/*    */   extends AbstractMapBag<E>
/*    */   implements Bag<E>, Serializable
/*    */ {
/*    */   static final long serialVersionUID = -6561115435802554013L;
/*    */   
/*    */   public HashBag()
/*    */   {
/* 54 */     super(new HashMap());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public HashBag(Collection<? extends E> coll)
/*    */   {
/* 63 */     this();
/* 64 */     addAll(coll);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private void writeObject(ObjectOutputStream out)
/*    */     throws IOException
/*    */   {
/* 72 */     out.defaultWriteObject();
/* 73 */     super.doWriteObject(out);
/*    */   }
/*    */   
/*    */ 
/*    */   private void readObject(ObjectInputStream in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 80 */     in.defaultReadObject();
/* 81 */     super.doReadObject(new HashMap(), in);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/HashBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */