/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformedMap
/*     */   extends AbstractInputCheckedMapDecorator
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7023152376788900464L;
/*     */   protected final Transformer keyTransformer;
/*     */   protected final Transformer valueTransformer;
/*     */   
/*     */   public static Map decorate(Map map, Transformer keyTransformer, Transformer valueTransformer)
/*     */   {
/*  72 */     return new TransformedMap(map, keyTransformer, valueTransformer);
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
/*     */   protected TransformedMap(Map map, Transformer keyTransformer, Transformer valueTransformer)
/*     */   {
/*  88 */     super(map);
/*  89 */     this.keyTransformer = keyTransformer;
/*  90 */     this.valueTransformer = valueTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 102 */     out.defaultWriteObject();
/* 103 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 115 */     in.defaultReadObject();
/* 116 */     this.map = ((Map)in.readObject());
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
/*     */   protected Object transformKey(Object object)
/*     */   {
/* 129 */     if (this.keyTransformer == null) {
/* 130 */       return object;
/*     */     }
/* 132 */     return this.keyTransformer.transform(object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Object transformValue(Object object)
/*     */   {
/* 144 */     if (this.valueTransformer == null) {
/* 145 */       return object;
/*     */     }
/* 147 */     return this.valueTransformer.transform(object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Map transformMap(Map map)
/*     */   {
/* 159 */     Map result = new LinkedMap(map.size());
/* 160 */     for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
/* 161 */       Map.Entry entry = (Map.Entry)it.next();
/* 162 */       result.put(transformKey(entry.getKey()), transformValue(entry.getValue()));
/*     */     }
/* 164 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Object checkSetValue(Object value)
/*     */   {
/* 175 */     return this.valueTransformer.transform(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isSetValueChecking()
/*     */   {
/* 185 */     return this.valueTransformer != null;
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value)
/*     */   {
/* 190 */     key = transformKey(key);
/* 191 */     value = transformValue(value);
/* 192 */     return getMap().put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 196 */     mapToCopy = transformMap(mapToCopy);
/* 197 */     getMap().putAll(mapToCopy);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/TransformedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */