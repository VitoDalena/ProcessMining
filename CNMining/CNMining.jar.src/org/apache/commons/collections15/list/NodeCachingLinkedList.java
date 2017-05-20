/*     */ package org.apache.commons.collections15.list;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
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
/*     */ public class NodeCachingLinkedList<E>
/*     */   extends AbstractLinkedList<E>
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 6897789178562232073L;
/*     */   protected static final int DEFAULT_MAXIMUM_CACHE_SIZE = 20;
/*     */   protected transient AbstractLinkedList.Node<E> firstCachedNode;
/*     */   protected transient int cacheSize;
/*     */   protected int maximumCacheSize;
/*     */   
/*     */   public NodeCachingLinkedList()
/*     */   {
/*  80 */     this(20);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NodeCachingLinkedList(Collection<E> coll)
/*     */   {
/*  89 */     super(coll);
/*  90 */     this.maximumCacheSize = 20;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NodeCachingLinkedList(int maximumCacheSize)
/*     */   {
/* 100 */     this.maximumCacheSize = maximumCacheSize;
/* 101 */     init();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int getMaximumCacheSize()
/*     */   {
/* 111 */     return this.maximumCacheSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setMaximumCacheSize(int maximumCacheSize)
/*     */   {
/* 120 */     this.maximumCacheSize = maximumCacheSize;
/* 121 */     shrinkCacheToMaximumSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void shrinkCacheToMaximumSize()
/*     */   {
/* 129 */     while (this.cacheSize > this.maximumCacheSize) {
/* 130 */       getNodeFromCache();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AbstractLinkedList.Node<E> getNodeFromCache()
/*     */   {
/* 142 */     if (this.cacheSize == 0) {
/* 143 */       return null;
/*     */     }
/* 145 */     AbstractLinkedList.Node<E> cachedNode = this.firstCachedNode;
/* 146 */     this.firstCachedNode = cachedNode.next;
/* 147 */     cachedNode.next = null;
/*     */     
/* 149 */     this.cacheSize -= 1;
/* 150 */     return cachedNode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isCacheFull()
/*     */   {
/* 159 */     return this.cacheSize >= this.maximumCacheSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addNodeToCache(AbstractLinkedList.Node<E> node)
/*     */   {
/* 169 */     if (isCacheFull())
/*     */     {
/* 171 */       return;
/*     */     }
/*     */     
/* 174 */     AbstractLinkedList.Node<E> nextCachedNode = this.firstCachedNode;
/* 175 */     node.previous = null;
/* 176 */     node.next = nextCachedNode;
/* 177 */     node.setValue(null);
/* 178 */     this.firstCachedNode = node;
/* 179 */     this.cacheSize += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AbstractLinkedList.Node<E> createNode(E value)
/*     */   {
/* 191 */     AbstractLinkedList.Node<E> cachedNode = getNodeFromCache();
/* 192 */     if (cachedNode == null) {
/* 193 */       return super.createNode(value);
/*     */     }
/* 195 */     cachedNode.setValue(value);
/* 196 */     return cachedNode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void removeNode(AbstractLinkedList.Node<E> node)
/*     */   {
/* 207 */     super.removeNode(node);
/* 208 */     addNodeToCache(node);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void removeAllNodes()
/*     */   {
/* 220 */     int numberOfNodesToCache = Math.min(this.size, this.maximumCacheSize - this.cacheSize);
/* 221 */     AbstractLinkedList.Node<E> node = this.header.next;
/* 222 */     for (int currentIndex = 0; currentIndex < numberOfNodesToCache; currentIndex++) {
/* 223 */       AbstractLinkedList.Node<E> oldNode = node;
/* 224 */       node = node.next;
/* 225 */       addNodeToCache(oldNode);
/*     */     }
/* 227 */     super.removeAllNodes();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 235 */     out.defaultWriteObject();
/* 236 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 243 */     in.defaultReadObject();
/* 244 */     doReadObject(in);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/NodeCachingLinkedList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */