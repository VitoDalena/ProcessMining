/*    */ package com.thoughtworks.xstream.persistence;
/*    */ 
/*    */ import java.util.AbstractList;
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
/*    */ public class XmlArrayList
/*    */   extends AbstractList
/*    */ {
/*    */   private final XmlMap map;
/*    */   
/*    */   public XmlArrayList(PersistenceStrategy persistenceStrategy)
/*    */   {
/* 26 */     this.map = new XmlMap(persistenceStrategy);
/*    */   }
/*    */   
/*    */   public int size() {
/* 30 */     return this.map.size();
/*    */   }
/*    */   
/*    */   public Object set(int index, Object element) {
/* 34 */     rangeCheck(index);
/* 35 */     Object value = get(index);
/* 36 */     this.map.put(new Integer(index), element);
/* 37 */     return value;
/*    */   }
/*    */   
/*    */   public void add(int index, Object element) {
/* 41 */     int size = size();
/* 42 */     if ((index >= size + 1) || (index < 0)) {
/* 43 */       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
/*    */     }
/*    */     
/* 46 */     int to = index != size ? index - 1 : index;
/* 47 */     for (int i = size; i > to; i--) {
/* 48 */       this.map.put(new Integer(i + 1), this.map.get(new Integer(i)));
/*    */     }
/* 50 */     this.map.put(new Integer(index), element);
/*    */   }
/*    */   
/*    */   private void rangeCheck(int index) {
/* 54 */     int size = size();
/* 55 */     if ((index >= size) || (index < 0)) {
/* 56 */       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
/*    */     }
/*    */   }
/*    */   
/*    */   public Object get(int index)
/*    */   {
/* 62 */     rangeCheck(index);
/* 63 */     return this.map.get(new Integer(index));
/*    */   }
/*    */   
/*    */   public Object remove(int index) {
/* 67 */     int size = size();
/* 68 */     rangeCheck(index);
/* 69 */     Object value = this.map.get(new Integer(index));
/* 70 */     for (int i = index; i < size - 1; i++) {
/* 71 */       this.map.put(new Integer(i), this.map.get(new Integer(i + 1)));
/*    */     }
/* 73 */     this.map.remove(new Integer(size - 1));
/* 74 */     return value;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/persistence/XmlArrayList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */