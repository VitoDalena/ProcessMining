/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import org.apache.commons.collections15.FunctorException;
/*     */ import org.apache.commons.collections15.Transformer;
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
/*     */ public class InvokerTransformer
/*     */   implements Transformer, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -8653385846894047688L;
/*     */   private final String iMethodName;
/*     */   private final Class[] iParamTypes;
/*     */   private final Object[] iArgs;
/*     */   
/*     */   public static Transformer getInstance(String methodName)
/*     */   {
/*  61 */     if (methodName == null) {
/*  62 */       throw new IllegalArgumentException("The method to invoke must not be null");
/*     */     }
/*  64 */     return new InvokerTransformer(methodName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Transformer getInstance(String methodName, Class[] paramTypes, Object[] args)
/*     */   {
/*  76 */     if (methodName == null) {
/*  77 */       throw new IllegalArgumentException("The method to invoke must not be null");
/*     */     }
/*  79 */     if (((paramTypes == null) && (args != null)) || ((paramTypes != null) && (args == null)) || ((paramTypes != null) && (args != null) && (paramTypes.length != args.length))) {
/*  80 */       throw new IllegalArgumentException("The parameter types must match the arguments");
/*     */     }
/*  82 */     if ((paramTypes == null) || (paramTypes.length == 0)) {
/*  83 */       return new InvokerTransformer(methodName);
/*     */     }
/*  85 */     paramTypes = (Class[])paramTypes.clone();
/*  86 */     args = (Object[])args.clone();
/*  87 */     return new InvokerTransformer(methodName, paramTypes, args);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private InvokerTransformer(String methodName)
/*     */   {
/*  98 */     this.iMethodName = methodName;
/*  99 */     this.iParamTypes = null;
/* 100 */     this.iArgs = null;
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
/*     */   public InvokerTransformer(String methodName, Class[] paramTypes, Object[] args)
/*     */   {
/* 113 */     this.iMethodName = methodName;
/* 114 */     this.iParamTypes = paramTypes;
/* 115 */     this.iArgs = args;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object transform(Object input)
/*     */   {
/* 125 */     if (input == null) {
/* 126 */       return null;
/*     */     }
/*     */     try {
/* 129 */       Class cls = input.getClass();
/* 130 */       Method method = cls.getMethod(this.iMethodName, this.iParamTypes);
/* 131 */       return method.invoke(input, this.iArgs);
/*     */     }
/*     */     catch (NoSuchMethodException ex) {
/* 134 */       throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + input.getClass() + "' does not exist");
/*     */     } catch (IllegalAccessException ex) {
/* 136 */       throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + input.getClass() + "' cannot be accessed");
/*     */     } catch (InvocationTargetException ex) {
/* 138 */       throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + input.getClass() + "' threw an exception", ex);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/InvokerTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */