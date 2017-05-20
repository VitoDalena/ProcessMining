/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.FunctorException;
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
/*     */ public class InstantiateFactory<T>
/*     */   implements Factory<T>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -7732226881069447957L;
/*     */   private final Class<T> iClassToInstantiate;
/*     */   private final Class[] iParamTypes;
/*     */   private final Object[] iArgs;
/*  55 */   private transient Constructor iConstructor = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Factory<T> getInstance(Class<T> classToInstantiate, Class[] paramTypes, Object[] args)
/*     */   {
/*  66 */     if (classToInstantiate == null) {
/*  67 */       throw new IllegalArgumentException("Class to instantiate must not be null");
/*     */     }
/*  69 */     if (((paramTypes == null) && (args != null)) || ((paramTypes != null) && (args == null)) || ((paramTypes != null) && (args != null) && (paramTypes.length != args.length))) {
/*  70 */       throw new IllegalArgumentException("Parameter types must match the arguments");
/*     */     }
/*     */     
/*  73 */     if ((paramTypes == null) || (paramTypes.length == 0)) {
/*  74 */       return new InstantiateFactory(classToInstantiate);
/*     */     }
/*  76 */     paramTypes = (Class[])paramTypes.clone();
/*  77 */     args = (Object[])args.clone();
/*  78 */     return new InstantiateFactory(classToInstantiate, paramTypes, args);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public InstantiateFactory(Class<T> classToInstantiate)
/*     */   {
/*  90 */     this.iClassToInstantiate = classToInstantiate;
/*  91 */     this.iParamTypes = null;
/*  92 */     this.iArgs = null;
/*  93 */     findConstructor();
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
/*     */   public InstantiateFactory(Class<T> classToInstantiate, Class[] paramTypes, Object[] args)
/*     */   {
/* 106 */     this.iClassToInstantiate = classToInstantiate;
/* 107 */     this.iParamTypes = paramTypes;
/* 108 */     this.iArgs = args;
/* 109 */     findConstructor();
/*     */   }
/*     */   
/*     */ 
/*     */   private void findConstructor()
/*     */   {
/*     */     try
/*     */     {
/* 117 */       this.iConstructor = this.iClassToInstantiate.getConstructor(this.iParamTypes);
/*     */     }
/*     */     catch (NoSuchMethodException ex) {
/* 120 */       throw new IllegalArgumentException("InstantiateFactory: The constructor must exist and be public ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public T create()
/*     */   {
/* 131 */     if (this.iConstructor == null) {
/* 132 */       findConstructor();
/*     */     }
/*     */     try
/*     */     {
/* 136 */       return (T)this.iConstructor.newInstance(this.iArgs);
/*     */     }
/*     */     catch (InstantiationException ex) {
/* 139 */       throw new FunctorException("InstantiateFactory: InstantiationException", ex);
/*     */     } catch (IllegalAccessException ex) {
/* 141 */       throw new FunctorException("InstantiateFactory: Constructor must be public", ex);
/*     */     } catch (InvocationTargetException ex) {
/* 143 */       throw new FunctorException("InstantiateFactory: Constructor threw an exception", ex);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/InstantiateFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */