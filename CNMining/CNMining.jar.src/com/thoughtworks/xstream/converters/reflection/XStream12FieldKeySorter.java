/*    */ package com.thoughtworks.xstream.converters.reflection;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
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
/*    */ public class XStream12FieldKeySorter
/*    */   implements FieldKeySorter
/*    */ {
/*    */   public Map sort(Class type, Map keyedByFieldKey)
/*    */   {
/* 28 */     Map map = new TreeMap(new Comparator()
/*    */     {
/*    */       public int compare(Object o1, Object o2) {
/* 31 */         FieldKey fieldKey1 = (FieldKey)o1;
/* 32 */         FieldKey fieldKey2 = (FieldKey)o2;
/* 33 */         int i = fieldKey2.getDepth() - fieldKey1.getDepth();
/* 34 */         if (i == 0) {
/* 35 */           i = fieldKey1.getOrder() - fieldKey2.getOrder();
/*    */         }
/* 37 */         return i;
/*    */       }
/* 39 */     });
/* 40 */     map.putAll(keyedByFieldKey);
/* 41 */     return map;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/XStream12FieldKeySorter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */