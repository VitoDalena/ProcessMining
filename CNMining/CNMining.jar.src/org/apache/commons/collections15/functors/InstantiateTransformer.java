/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
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
/*     */ public class InstantiateTransformer
/*     */   implements Transformer<Class, Object>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 3786388740793356347L;
/*  43 */   public static final InstantiateTransformer NO_ARG_INSTANCE = new InstantiateTransformer();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final Class[] iParamTypes;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final Object[] iArgs;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static InstantiateTransformer getInstance(Class[] paramTypes, Object[] args)
/*     */   {
/*  62 */     if (((paramTypes == null) && (args != null)) || ((paramTypes != null) && (args == null)) || ((paramTypes != null) && (args != null) && (paramTypes.length != args.length))) {
/*  63 */       throw new IllegalArgumentException("Parameter types must match the arguments");
/*     */     }
/*     */     
/*  66 */     if ((paramTypes == null) || (paramTypes.length == 0)) {
/*  67 */       return NO_ARG_INSTANCE;
/*     */     }
/*  69 */     paramTypes = (Class[])paramTypes.clone();
/*  70 */     args = (Object[])args.clone();
/*     */     
/*  72 */     return new InstantiateTransformer(paramTypes, args);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private InstantiateTransformer()
/*     */   {
/*  80 */     this.iParamTypes = null;
/*  81 */     this.iArgs = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public InstantiateTransformer(Class[] paramTypes, Object[] args)
/*     */   {
/*  93 */     this.iParamTypes = paramTypes;
/*  94 */     this.iArgs = args;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object transform(Class input)
/*     */   {
/*     */     try
/*     */     {
/* 105 */       Constructor con = input.getConstructor(this.iParamTypes);
/* 106 */       return con.newInstance(this.iArgs);
/*     */     }
/*     */     catch (NoSuchMethodException ex) {
/* 109 */       throw new FunctorException("InstantiateTransformer: The constructor must exist and be public ");
/*     */     } catch (InstantiationException ex) {
/* 111 */       throw new FunctorException("InstantiateTransformer: InstantiationException", ex);
/*     */     } catch (IllegalAccessException ex) {
/* 113 */       throw new FunctorException("InstantiateTransformer: Constructor must be public", ex);
/*     */     } catch (InvocationTargetException ex) {
/* 115 */       throw new FunctorException("InstantiateTransformer: Constructor threw an exception", ex);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/InstantiateTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */