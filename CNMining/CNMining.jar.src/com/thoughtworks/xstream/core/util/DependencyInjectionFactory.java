/*     */ package com.thoughtworks.xstream.core.util;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.BitSet;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
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
/*     */ public class DependencyInjectionFactory
/*     */ {
/*     */   public static Object newInstance(Class type, Object[] dependencies)
/*     */   {
/*  46 */     return newInstance(type, dependencies, new BitSet());
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
/*     */   public static Object newInstance(Class type, Object[] dependencies, BitSet usedDependencies)
/*     */   {
/*  64 */     Constructor bestMatchingCtor = null;
/*  65 */     List matchingDependencies = new ArrayList();
/*     */     
/*  67 */     if ((dependencies != null) && (dependencies.length > 0))
/*     */     {
/*  69 */       Constructor[] ctors = type.getConstructors();
/*  70 */       if (ctors.length > 1) {
/*  71 */         Arrays.sort(ctors, new Comparator() {
/*     */           public int compare(Object o1, Object o2) {
/*  73 */             return ((Constructor)o2).getParameterTypes().length - ((Constructor)o1).getParameterTypes().length;
/*     */           }
/*     */         });
/*     */       }
/*     */       
/*     */ 
/*  79 */       TypedValue[] typedDependencies = new TypedValue[dependencies.length];
/*  80 */       for (int i = 0; i < dependencies.length; i++) {
/*  81 */         Object dependency = dependencies[i];
/*  82 */         Class depType = dependency.getClass();
/*  83 */         if (depType.isPrimitive()) {
/*  84 */           depType = Primitives.box(depType);
/*  85 */         } else if (depType == TypedNull.class) {
/*  86 */           depType = ((TypedNull)dependency).getType();
/*  87 */           dependency = null;
/*     */         }
/*     */         
/*  90 */         typedDependencies[i] = new TypedValue(depType, dependency);
/*     */       }
/*     */       
/*  93 */       Constructor possibleCtor = null;
/*  94 */       int arity = Integer.MAX_VALUE;
/*  95 */       for (int i = 0; (bestMatchingCtor == null) && (i < ctors.length); i++) {
/*  96 */         Constructor constructor = ctors[i];
/*  97 */         Class[] parameterTypes = constructor.getParameterTypes();
/*  98 */         if (parameterTypes.length <= dependencies.length)
/*     */         {
/* 100 */           if (parameterTypes.length == 0) {
/* 101 */             bestMatchingCtor = constructor;
/* 102 */             break;
/*     */           }
/* 104 */           if (arity > parameterTypes.length) {
/* 105 */             if (possibleCtor != null) {
/* 106 */               bestMatchingCtor = possibleCtor;
/*     */             }
/*     */             else {
/* 109 */               arity = parameterTypes.length;
/*     */             }
/*     */           } else {
/* 112 */             for (int j = 0; j < parameterTypes.length; j++) {
/* 113 */               if (parameterTypes[j].isPrimitive()) {
/* 114 */                 parameterTypes[j] = Primitives.box(parameterTypes[j]);
/*     */               }
/*     */             }
/*     */             
/*     */ 
/*     */ 
/*     */ 
/* 121 */             matchingDependencies.clear();
/* 122 */             for (int j = usedDependencies.length(); j-- > 0;) {
/* 123 */               usedDependencies.clear(j);
/*     */             }
/* 125 */             int j = 0; for (int k = 0; 
/* 126 */                 (j < parameterTypes.length) && (parameterTypes.length + k - j <= typedDependencies.length); k++) {
/* 127 */               if (parameterTypes[j].isAssignableFrom(typedDependencies[k].type)) {
/* 128 */                 matchingDependencies.add(typedDependencies[k].value);
/* 129 */                 usedDependencies.set(k);
/* 130 */                 j++; if (j == parameterTypes.length) {
/* 131 */                   bestMatchingCtor = constructor;
/* 132 */                   break;
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/* 137 */             if ((bestMatchingCtor == null) && (possibleCtor == null)) {
/* 138 */               possibleCtor = constructor;
/*     */               
/*     */ 
/*     */ 
/* 142 */               TypedValue[] deps = new TypedValue[typedDependencies.length];
/* 143 */               System.arraycopy(typedDependencies, 0, deps, 0, deps.length);
/* 144 */               matchingDependencies.clear();
/* 145 */               for (int j = usedDependencies.length(); j-- > 0;) {
/* 146 */                 usedDependencies.clear(j);
/*     */               }
/* 148 */               for (int j = 0; j < parameterTypes.length; j++) {
/* 149 */                 int assignable = -1;
/* 150 */                 for (int k = 0; k < deps.length; k++) {
/* 151 */                   if (deps[k] != null)
/*     */                   {
/*     */ 
/* 154 */                     if (deps[k].type == parameterTypes[j]) {
/* 155 */                       assignable = k;
/*     */                       
/* 157 */                       break; }
/* 158 */                     if (parameterTypes[j].isAssignableFrom(deps[k].type))
/*     */                     {
/* 160 */                       if ((assignable < 0) || ((deps[assignable].type != deps[k].type) && (deps[assignable].type.isAssignableFrom(deps[k].type))))
/*     */                       {
/*     */ 
/* 163 */                         assignable = k;
/*     */                       }
/*     */                     }
/*     */                   }
/*     */                 }
/* 168 */                 if (assignable >= 0) {
/* 169 */                   matchingDependencies.add(deps[assignable].value);
/* 170 */                   usedDependencies.set(assignable);
/* 171 */                   deps[assignable] = null;
/*     */                 } else {
/* 173 */                   possibleCtor = null;
/* 174 */                   break;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         } }
/* 180 */       if (bestMatchingCtor == null) {
/* 181 */         if (possibleCtor == null) {
/* 182 */           for (int j = usedDependencies.length(); j-- > 0;) {
/* 183 */             usedDependencies.clear(j);
/*     */           }
/* 185 */           throw new ObjectAccessException("Cannot construct " + type.getName() + ", none of the dependencies match any constructor's parameters");
/*     */         }
/*     */         
/*     */ 
/* 189 */         bestMatchingCtor = possibleCtor;
/*     */       }
/*     */     }
/*     */     
/*     */     try
/*     */     {
/*     */       Object instance;
/* 196 */       if (bestMatchingCtor == null) {
/* 197 */         instance = type.newInstance();
/*     */       }
/* 199 */       return bestMatchingCtor.newInstance(matchingDependencies.toArray());
/*     */     }
/*     */     catch (InstantiationException e)
/*     */     {
/* 203 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (IllegalAccessException e) {
/* 205 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     } catch (InvocationTargetException e) {
/* 207 */       throw new ObjectAccessException("Cannot construct " + type.getName(), e);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class TypedValue
/*     */   {
/*     */     final Class type;
/*     */     final Object value;
/*     */     
/*     */     public TypedValue(Class type, Object value) {
/* 217 */       this.type = type;
/* 218 */       this.value = value;
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/* 223 */       return this.type.getName() + ":" + this.value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/DependencyInjectionFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */