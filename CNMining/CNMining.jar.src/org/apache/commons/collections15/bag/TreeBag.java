/*     */ package org.apache.commons.collections15.bag;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ import org.apache.commons.collections15.SortedBag;
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
/*     */ public class TreeBag<E>
/*     */   extends AbstractMapBag<E>
/*     */   implements SortedBag<E>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -7740146511091606676L;
/*     */   
/*     */   public TreeBag()
/*     */   {
/*  59 */     super(new TreeMap());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TreeBag(Comparator<? super E> comparator)
/*     */   {
/*  69 */     super(new TreeMap(comparator));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TreeBag(Collection<? extends E> coll)
/*     */   {
/*  79 */     this();
/*  80 */     addAll(coll);
/*     */   }
/*     */   
/*     */   public E first()
/*     */   {
/*  85 */     return (E)((SortedMap)getMap()).firstKey();
/*     */   }
/*     */   
/*     */   public E last() {
/*  89 */     return (E)((SortedMap)getMap()).lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  93 */     return ((SortedMap)getMap()).comparator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 101 */     out.defaultWriteObject();
/* 102 */     out.writeObject(comparator());
/* 103 */     super.doWriteObject(out);
/*     */   }
/*     */   
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 110 */     in.defaultReadObject();
/* 111 */     Comparator comp = (Comparator)in.readObject();
/* 112 */     super.doReadObject(new TreeMap(comp), in);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/TreeBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */