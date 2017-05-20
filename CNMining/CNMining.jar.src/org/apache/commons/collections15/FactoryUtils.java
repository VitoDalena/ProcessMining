/*     */ package org.apache.commons.collections15;
/*     */ 
/*     */ import org.apache.commons.collections15.functors.ConstantFactory;
/*     */ import org.apache.commons.collections15.functors.ExceptionFactory;
/*     */ import org.apache.commons.collections15.functors.InstantiateFactory;
/*     */ import org.apache.commons.collections15.functors.PrototypeFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FactoryUtils
/*     */ {
/*     */   public static Factory exceptionFactory()
/*     */   {
/*  57 */     return ExceptionFactory.INSTANCE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Factory nullFactory()
/*     */   {
/*  68 */     return ConstantFactory.NULL_INSTANCE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Factory<T> constantFactory(T constantToReturn)
/*     */   {
/*  82 */     return ConstantFactory.getInstance(constantToReturn);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Factory<T> prototypeFactory(T prototype)
/*     */   {
/* 102 */     return PrototypeFactory.getInstance(prototype);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Factory<T> instantiateFactory(Class<T> classToInstantiate)
/*     */   {
/* 115 */     return InstantiateFactory.getInstance(classToInstantiate, null, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Factory<T> instantiateFactory(Class<T> classToInstantiate, Class[] paramTypes, Object[] args)
/*     */   {
/* 132 */     return InstantiateFactory.getInstance(classToInstantiate, paramTypes, args);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/FactoryUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */