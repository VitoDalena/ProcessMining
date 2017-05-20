/*    */ package com.thoughtworks.xstream.persistence;
/*    */ 
/*    */ import java.util.AbstractMap;
/*    */ import java.util.AbstractSet;
/*    */ import java.util.Iterator;
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
/*    */ public class XmlMap
/*    */   extends AbstractMap
/*    */ {
/*    */   private final PersistenceStrategy persistenceStrategy;
/*    */   
/*    */   public XmlMap(PersistenceStrategy streamStrategy)
/*    */   {
/* 31 */     this.persistenceStrategy = streamStrategy;
/*    */   }
/*    */   
/*    */   public int size() {
/* 35 */     return this.persistenceStrategy.size();
/*    */   }
/*    */   
/*    */   public Object get(Object key)
/*    */   {
/* 40 */     return this.persistenceStrategy.get(key);
/*    */   }
/*    */   
/*    */   public Object put(Object key, Object value) {
/* 44 */     return this.persistenceStrategy.put(key, value);
/*    */   }
/*    */   
/*    */   public Object remove(Object key) {
/* 48 */     return this.persistenceStrategy.remove(key);
/*    */   }
/*    */   
/*    */   public Set entrySet() {
/* 52 */     return new XmlMapEntries();
/*    */   }
/*    */   
/*    */   class XmlMapEntries extends AbstractSet {
/*    */     XmlMapEntries() {}
/*    */     
/* 58 */     public int size() { return XmlMap.this.size(); }
/*    */     
/*    */     public boolean isEmpty()
/*    */     {
/* 62 */       return XmlMap.this.isEmpty();
/*    */     }
/*    */     
/*    */     public Iterator iterator() {
/* 66 */       return XmlMap.this.persistenceStrategy.iterator();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/persistence/XmlMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */