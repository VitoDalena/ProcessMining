/*    */ package com.thoughtworks.xstream.persistence;
/*    */ 
/*    */ import java.util.AbstractSet;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
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
/*    */ public class XmlSet
/*    */   extends AbstractSet
/*    */ {
/*    */   private final XmlMap map;
/*    */   
/*    */   public XmlSet(PersistenceStrategy persistenceStrategy)
/*    */   {
/* 27 */     this.map = new XmlMap(persistenceStrategy);
/*    */   }
/*    */   
/*    */   public Iterator iterator() {
/* 31 */     return this.map.values().iterator();
/*    */   }
/*    */   
/*    */   public int size() {
/* 35 */     return this.map.size();
/*    */   }
/*    */   
/*    */   public boolean add(Object o) {
/* 39 */     if (this.map.containsValue(o)) {
/* 40 */       return false;
/*    */     }
/*    */     
/* 43 */     this.map.put(findEmptyKey(), o);
/* 44 */     return true;
/*    */   }
/*    */   
/*    */   private Long findEmptyKey()
/*    */   {
/* 49 */     long i = System.currentTimeMillis();
/* 50 */     while (this.map.containsKey(new Long(i))) {
/* 51 */       i += 1L;
/*    */     }
/* 53 */     return new Long(i);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/persistence/XmlSet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */