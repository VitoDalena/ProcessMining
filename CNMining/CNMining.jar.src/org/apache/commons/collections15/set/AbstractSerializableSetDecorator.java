/*    */ package org.apache.commons.collections15.set;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import java.util.Set;
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
/*    */ public abstract class AbstractSerializableSetDecorator<E>
/*    */   extends AbstractSetDecorator<E>
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1229469966212206107L;
/*    */   
/*    */   protected AbstractSerializableSetDecorator(Set<E> set)
/*    */   {
/* 43 */     super(set);
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/AbstractSerializableSetDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */