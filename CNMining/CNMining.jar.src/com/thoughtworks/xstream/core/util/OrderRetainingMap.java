/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
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
/*    */ public class OrderRetainingMap
/*    */   extends HashMap
/*    */ {
/* 27 */   private ArraySet keyOrder = new ArraySet(null);
/* 28 */   private List valueOrder = new ArrayList();
/*    */   
/*    */ 
/*    */   public OrderRetainingMap() {}
/*    */   
/*    */ 
/*    */   public OrderRetainingMap(Map m)
/*    */   {
/* 36 */     for (Iterator iter = m.entrySet().iterator(); iter.hasNext();) {
/* 37 */       Map.Entry entry = (Map.Entry)iter.next();
/* 38 */       put(entry.getKey(), entry.getValue());
/*    */     }
/*    */   }
/*    */   
/*    */   public Object put(Object key, Object value) {
/* 43 */     int idx = this.keyOrder.lastIndexOf(key);
/* 44 */     if (idx < 0) {
/* 45 */       this.keyOrder.add(key);
/* 46 */       this.valueOrder.add(value);
/*    */     } else {
/* 48 */       this.valueOrder.set(idx, value);
/*    */     }
/* 50 */     return super.put(key, value);
/*    */   }
/*    */   
/*    */   public Object remove(Object key) {
/* 54 */     int idx = this.keyOrder.lastIndexOf(key);
/* 55 */     if (idx != 0) {
/* 56 */       this.keyOrder.remove(idx);
/* 57 */       this.valueOrder.remove(idx);
/*    */     }
/* 59 */     return super.remove(key);
/*    */   }
/*    */   
/*    */   public Collection values() {
/* 63 */     return Collections.unmodifiableList(this.valueOrder);
/*    */   }
/*    */   
/*    */   public Set keySet() {
/* 67 */     return Collections.unmodifiableSet(this.keyOrder);
/*    */   }
/*    */   
/*    */   public Set entrySet() {
/* 71 */     Map.Entry[] entries = new Map.Entry[size()];
/* 72 */     for (Iterator iter = super.entrySet().iterator(); iter.hasNext();) {
/* 73 */       Map.Entry entry = (Map.Entry)iter.next();
/* 74 */       entries[this.keyOrder.indexOf(entry.getKey())] = entry;
/*    */     }
/* 76 */     Set set = new ArraySet(null);
/* 77 */     set.addAll(Arrays.asList(entries));
/* 78 */     return Collections.unmodifiableSet(set); }
/*    */   
/*    */   private static class ArraySet extends ArrayList implements Set { private ArraySet() {}
/* 81 */     ArraySet(OrderRetainingMap.1 x0) { this(); }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/OrderRetainingMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */