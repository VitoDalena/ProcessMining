/*    */ package com.thoughtworks.xstream.mapper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OuterClassMapper
/*    */   extends MapperWrapper
/*    */ {
/*    */   private final String alias;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public OuterClassMapper(Mapper wrapped)
/*    */   {
/* 24 */     this(wrapped, "outer-class");
/*    */   }
/*    */   
/*    */   public OuterClassMapper(Mapper wrapped, String alias) {
/* 28 */     super(wrapped);
/* 29 */     this.alias = alias;
/*    */   }
/*    */   
/*    */   public String serializedMember(Class type, String memberName) {
/* 33 */     if (memberName.equals("this$0")) {
/* 34 */       return this.alias;
/*    */     }
/* 36 */     return super.serializedMember(type, memberName);
/*    */   }
/*    */   
/*    */   public String realMember(Class type, String serialized)
/*    */   {
/* 41 */     if (serialized.equals(this.alias)) {
/* 42 */       return "this$0";
/*    */     }
/* 44 */     return super.realMember(type, serialized);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/OuterClassMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */