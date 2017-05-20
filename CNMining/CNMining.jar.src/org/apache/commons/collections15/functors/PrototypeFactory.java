/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
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
/*     */ public class PrototypeFactory<T>
/*     */ {
/*     */   public static <T> Factory<T> getInstance(T prototype)
/*     */   {
/*  53 */     if (prototype == null) {
/*  54 */       return ConstantFactory.NULL_INSTANCE;
/*     */     }
/*     */     try {
/*  57 */       Method method = prototype.getClass().getMethod("clone", null);
/*  58 */       return new PrototypeCloneFactory(prototype, method, null);
/*     */     }
/*     */     catch (NoSuchMethodException ex) {
/*     */       try {
/*  62 */         prototype.getClass().getConstructor(new Class[] { prototype.getClass() });
/*  63 */         return new InstantiateFactory(prototype.getClass(), new Class[] { prototype.getClass() }, new Object[] { prototype });
/*     */       }
/*     */       catch (NoSuchMethodException ex2) {
/*  66 */         if ((prototype instanceof Serializable)) {
/*  67 */           return new PrototypeSerializationFactory((Serializable)prototype, null);
/*     */         }
/*     */         
/*     */ 
/*  71 */         throw new IllegalArgumentException("The prototype must be cloneable via a public clone method");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static class PrototypeCloneFactory<T>
/*     */     implements Factory<T>, Serializable
/*     */   {
/*     */     static final long serialVersionUID = 5604271422565175555L;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private final T iPrototype;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private transient Method iCloneMethod;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private PrototypeCloneFactory(T prototype, Method method)
/*     */     {
/* 108 */       this.iPrototype = prototype;
/* 109 */       this.iCloneMethod = method;
/*     */     }
/*     */     
/*     */ 
/*     */     private void findCloneMethod()
/*     */     {
/*     */       try
/*     */       {
/* 117 */         this.iCloneMethod = this.iPrototype.getClass().getMethod("clone", null);
/*     */       }
/*     */       catch (NoSuchMethodException ex) {
/* 120 */         throw new IllegalArgumentException("PrototypeCloneFactory: The clone method must exist and be public ");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public T create()
/*     */     {
/* 131 */       if (this.iCloneMethod == null) {
/* 132 */         findCloneMethod();
/*     */       }
/*     */       try
/*     */       {
/* 136 */         return (T)this.iCloneMethod.invoke(this.iPrototype, null);
/*     */       }
/*     */       catch (IllegalAccessException ex) {
/* 139 */         throw new FunctorException("PrototypeCloneFactory: Clone method must be public", ex);
/*     */       } catch (InvocationTargetException ex) {
/* 141 */         throw new FunctorException("PrototypeCloneFactory: Clone method threw an exception", ex);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static class PrototypeSerializationFactory<T extends Serializable>
/*     */     implements Factory<T>, Serializable
/*     */   {
/*     */     static final long serialVersionUID = -8704966966139178833L;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private final Serializable iPrototype;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private PrototypeSerializationFactory(Serializable prototype)
/*     */     {
/* 168 */       this.iPrototype = prototype;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public T create()
/*     */     {
/* 177 */       ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
/* 178 */       ByteArrayInputStream bais = null;
/*     */       try {
/* 180 */         ObjectOutputStream out = new ObjectOutputStream(baos);
/* 181 */         out.writeObject(this.iPrototype);
/*     */         
/* 183 */         bais = new ByteArrayInputStream(baos.toByteArray());
/* 184 */         ObjectInputStream in = new ObjectInputStream(bais);
/* 185 */         return (Serializable)in.readObject();
/*     */       }
/*     */       catch (ClassNotFoundException ex) {
/* 188 */         throw new FunctorException(ex);
/*     */       } catch (IOException ex) {
/* 190 */         throw new FunctorException(ex);
/*     */       } finally {
/*     */         try {
/* 193 */           if (bais != null) {
/* 194 */             bais.close();
/*     */           }
/*     */         }
/*     */         catch (IOException ex) {}
/*     */         try
/*     */         {
/* 200 */           if (baos != null) {
/* 201 */             baos.close();
/*     */           }
/*     */         }
/*     */         catch (IOException ex) {}
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/PrototypeFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */