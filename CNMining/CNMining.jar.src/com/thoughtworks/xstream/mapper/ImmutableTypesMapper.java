/*    */ package com.thoughtworks.xstream.mapper;
/*    */ 
/*    */ import java.util.HashSet;
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
/*    */ public class ImmutableTypesMapper
/*    */   extends MapperWrapper
/*    */ {
/* 25 */   private final Set immutableTypes = new HashSet();
/*    */   
/*    */   public ImmutableTypesMapper(Mapper wrapped) {
/* 28 */     super(wrapped);
/*    */   }
/*    */   
/*    */   public void addImmutableType(Class type) {
/* 32 */     this.immutableTypes.add(type);
/*    */   }
/*    */   
/*    */   public boolean isImmutableValueType(Class type) {
/* 36 */     if (this.immutableTypes.contains(type)) {
/* 37 */       return true;
/*    */     }
/* 39 */     return super.isImmutableValueType(type);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/ImmutableTypesMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */