/*    */ package org.apache.commons.collections15.list;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
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
/*    */ public abstract class AbstractSerializableListDecorator<E>
/*    */   extends AbstractListDecorator<E>
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2684959196747496299L;
/*    */   
/*    */   protected AbstractSerializableListDecorator(List<E> list)
/*    */   {
/* 43 */     super(list);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void writeObject(ObjectOutputStream out)
/*    */     throws IOException
/*    */   {
/* 54 */     out.defaultWriteObject();
/* 55 */     out.writeObject(this.collection);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void readObject(ObjectInputStream in)
/*    */     throws IOException, ClassNotFoundException
/*    */   {
/* 66 */     in.defaultReadObject();
/* 67 */     this.collection = ((Collection)in.readObject());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/AbstractSerializableListDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */