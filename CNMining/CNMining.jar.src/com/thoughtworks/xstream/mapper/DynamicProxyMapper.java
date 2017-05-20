/*    */ package com.thoughtworks.xstream.mapper;
/*    */ 
/*    */ import java.lang.reflect.Proxy;
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
/*    */ public class DynamicProxyMapper
/*    */   extends MapperWrapper
/*    */ {
/*    */   private String alias;
/*    */   
/*    */   public DynamicProxyMapper(Mapper wrapped)
/*    */   {
/* 27 */     this(wrapped, "dynamic-proxy");
/*    */   }
/*    */   
/*    */   public DynamicProxyMapper(Mapper wrapped, String alias) {
/* 31 */     super(wrapped);
/* 32 */     this.alias = alias;
/*    */   }
/*    */   
/*    */   public String getAlias() {
/* 36 */     return this.alias;
/*    */   }
/*    */   
/*    */   public void setAlias(String alias) {
/* 40 */     this.alias = alias;
/*    */   }
/*    */   
/*    */   public String serializedClass(Class type) {
/* 44 */     if (Proxy.isProxyClass(type)) {
/* 45 */       return this.alias;
/*    */     }
/* 47 */     return super.serializedClass(type);
/*    */   }
/*    */   
/*    */   public Class realClass(String elementName)
/*    */   {
/* 52 */     if (elementName.equals(this.alias)) {
/* 53 */       return DynamicProxy.class;
/*    */     }
/* 55 */     return super.realClass(elementName);
/*    */   }
/*    */   
/*    */   public static class DynamicProxy {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/DynamicProxyMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */