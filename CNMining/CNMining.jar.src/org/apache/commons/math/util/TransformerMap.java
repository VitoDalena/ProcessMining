/*     */ package org.apache.commons.math.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.math.MathException;
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
/*     */ public class TransformerMap
/*     */   implements NumberTransformer, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -942772950698439883L;
/*  42 */   private NumberTransformer defaultTransformer = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  47 */   private Map map = null;
/*     */   
/*     */ 
/*     */ 
/*     */   public TransformerMap()
/*     */   {
/*  53 */     this.map = new HashMap();
/*  54 */     this.defaultTransformer = new DefaultTransformer();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsClass(Class key)
/*     */   {
/*  63 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsTransformer(NumberTransformer value)
/*     */   {
/*  72 */     return this.map.containsValue(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberTransformer getTransformer(Class key)
/*     */   {
/*  82 */     return (NumberTransformer)this.map.get(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object putTransformer(Class key, NumberTransformer transformer)
/*     */   {
/*  94 */     return this.map.put(key, transformer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object removeTransformer(Class key)
/*     */   {
/* 104 */     return this.map.remove(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 111 */     this.map.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set classes()
/*     */   {
/* 119 */     return this.map.keySet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection transformers()
/*     */   {
/* 128 */     return this.map.values();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double transform(Object o)
/*     */     throws MathException
/*     */   {
/* 141 */     double value = NaN.0D;
/*     */     
/* 143 */     if (((o instanceof Number)) || ((o instanceof String))) {
/* 144 */       value = this.defaultTransformer.transform(o);
/*     */     } else {
/* 146 */       NumberTransformer trans = getTransformer(o.getClass());
/* 147 */       if (trans != null) {
/* 148 */         value = trans.transform(o);
/*     */       }
/*     */     }
/*     */     
/* 152 */     return value;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/util/TransformerMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */