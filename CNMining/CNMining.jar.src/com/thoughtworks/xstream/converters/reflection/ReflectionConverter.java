/*    */ package com.thoughtworks.xstream.converters.reflection;
/*    */ 
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
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
/*    */ public class ReflectionConverter
/*    */   extends AbstractReflectionConverter
/*    */ {
/*    */   public ReflectionConverter(Mapper mapper, ReflectionProvider reflectionProvider)
/*    */   {
/* 19 */     super(mapper, reflectionProvider);
/*    */   }
/*    */   
/*    */   public boolean canConvert(Class type) {
/* 23 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/ReflectionConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */