/*    */ package com.thoughtworks.xstream.converters.reflection;
/*    */ 
/*    */ import com.thoughtworks.xstream.core.util.OrderRetainingMap;
/*    */ import com.thoughtworks.xstream.io.StreamException;
/*    */ import java.util.Arrays;
/*    */ import java.util.Comparator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.WeakHashMap;
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
/*    */ public class SortableFieldKeySorter
/*    */   implements FieldKeySorter
/*    */ {
/*    */   private final Map map;
/*    */   
/* 30 */   public SortableFieldKeySorter() { this.map = new WeakHashMap(); }
/*    */   
/*    */   public Map sort(Class type, Map keyedByFieldKey) {
/* 33 */     if (this.map.containsKey(type)) {
/* 34 */       Map result = new OrderRetainingMap();
/* 35 */       FieldKey[] fieldKeys = (FieldKey[])keyedByFieldKey.keySet().toArray(new FieldKey[keyedByFieldKey.size()]);
/*    */       
/* 37 */       Arrays.sort(fieldKeys, (Comparator)this.map.get(type));
/* 38 */       for (int i = 0; i < fieldKeys.length; i++) {
/* 39 */         result.put(fieldKeys[i], keyedByFieldKey.get(fieldKeys[i]));
/*    */       }
/* 41 */       return result;
/*    */     }
/* 43 */     return keyedByFieldKey;
/*    */   }
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
/*    */   public void registerFieldOrder(Class type, String[] fields)
/*    */   {
/* 59 */     this.map.put(type, new FieldComparator(fields));
/*    */   }
/*    */   
/*    */   private class FieldComparator implements Comparator
/*    */   {
/*    */     private final String[] fieldOrder;
/*    */     
/*    */     public FieldComparator(String[] fields) {
/* 67 */       this.fieldOrder = fields;
/*    */     }
/*    */     
/*    */     public int compare(String first, String second) {
/* 71 */       int firstPosition = -1;int secondPosition = -1;
/* 72 */       for (int i = 0; i < this.fieldOrder.length; i++) {
/* 73 */         if (this.fieldOrder[i].equals(first)) {
/* 74 */           firstPosition = i;
/*    */         }
/* 76 */         if (this.fieldOrder[i].equals(second)) {
/* 77 */           secondPosition = i;
/*    */         }
/*    */       }
/* 80 */       if ((firstPosition == -1) || (secondPosition == -1))
/*    */       {
/* 82 */         throw new StreamException("You have not given XStream a list of all fields to be serialized.");
/*    */       }
/*    */       
/* 85 */       return firstPosition - secondPosition;
/*    */     }
/*    */     
/*    */     public int compare(Object firstObject, Object secondObject) {
/* 89 */       FieldKey first = (FieldKey)firstObject;FieldKey second = (FieldKey)secondObject;
/* 90 */       return compare(first.getFieldName(), second.getFieldName());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/SortableFieldKeySorter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */