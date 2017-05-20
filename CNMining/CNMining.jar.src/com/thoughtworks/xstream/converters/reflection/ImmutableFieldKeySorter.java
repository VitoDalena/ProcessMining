/*    */ package com.thoughtworks.xstream.converters.reflection;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ public class ImmutableFieldKeySorter
/*    */   implements FieldKeySorter
/*    */ {
/*    */   public Map sort(Class type, Map keyedByFieldKey)
/*    */   {
/* 24 */     return keyedByFieldKey;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/ImmutableFieldKeySorter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */