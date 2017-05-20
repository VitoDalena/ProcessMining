/*    */ package org.apache.commons.collections15.collection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
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
/*    */ public abstract class AbstractSerializableCollectionDecorator<E>
/*    */   extends AbstractCollectionDecorator<E>
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 6249888059822088500L;
/*    */   
/*    */   protected AbstractSerializableCollectionDecorator(Collection<E> coll)
/*    */   {
/* 42 */     super(coll);
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
/* 53 */     out.defaultWriteObject();
/* 54 */     out.writeObject(this.collection);
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
/* 65 */     in.defaultReadObject();
/* 66 */     this.collection = ((Collection)in.readObject());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/collection/AbstractSerializableCollectionDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */