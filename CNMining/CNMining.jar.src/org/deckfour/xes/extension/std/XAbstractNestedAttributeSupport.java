/*     */ package org.deckfour.xes.extension.std;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.model.XAttributable;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
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
/*     */ public abstract class XAbstractNestedAttributeSupport<Type>
/*     */ {
/*     */   public abstract Type extractValue(XAttribute paramXAttribute);
/*     */   
/*     */   public abstract void assignValue(XAttribute paramXAttribute, Type paramType);
/*     */   
/*     */   public Map<String, Type> extractValues(XAttributable element)
/*     */   {
/* 117 */     Map<String, Type> values = new HashMap();
/* 118 */     Map<List<String>, Type> nestedValues = extractNestedValues(element);
/*     */     
/*     */ 
/*     */ 
/* 122 */     for (List<String> keys : nestedValues.keySet()) {
/* 123 */       if (keys.size() == 1)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 128 */         values.put(keys.get(0), nestedValues.get(keys));
/*     */       }
/*     */     }
/* 131 */     return values;
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
/*     */   public Map<List<String>, Type> extractNestedValues(XAttributable element)
/*     */   {
/* 173 */     Map<List<String>, Type> nestedValues = new HashMap();
/* 174 */     for (XAttribute attr : element.getAttributes().values()) {
/* 175 */       List<String> keys = new ArrayList();
/* 176 */       keys.add(attr.getKey());
/* 177 */       extractNestedValuesPrivate(attr, nestedValues, keys);
/*     */     }
/* 179 */     return nestedValues;
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
/*     */   private void extractNestedValuesPrivate(XAttribute element, Map<List<String>, Type> nestedValues, List<String> keys)
/*     */   {
/* 192 */     Type value = extractValue(element);
/* 193 */     if (value != null) {
/* 194 */       nestedValues.put(keys, value);
/*     */     }
/* 196 */     for (XAttribute attr : element.getAttributes().values()) {
/* 197 */       List<String> newKeys = new ArrayList(keys);
/* 198 */       newKeys.add(element.getKey());
/* 199 */       extractNestedValuesPrivate(attr, nestedValues, newKeys);
/*     */     }
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
/*     */   public void assignValues(XAttributable element, Map<String, Type> values)
/*     */   {
/* 238 */     Map<List<String>, Type> nestedValues = new HashMap();
/* 239 */     for (String key : values.keySet()) {
/* 240 */       List<String> keys = new ArrayList();
/* 241 */       keys.add(key);
/* 242 */       nestedValues.put(keys, values.get(key));
/*     */     }
/* 244 */     assignNestedValues(element, nestedValues);
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
/*     */   public void assignNestedValues(XAttributable element, Map<List<String>, Type> amounts)
/*     */   {
/* 293 */     for (List<String> keys : amounts.keySet()) {
/* 294 */       assignNestedValuesPrivate(element, keys, amounts.get(keys));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void assignNestedValuesPrivate(XAttributable element, List<String> keys, Type value)
/*     */   {
/* 305 */     if (keys.isEmpty())
/*     */     {
/*     */ 
/*     */ 
/* 309 */       if ((element instanceof XAttribute)) {
/* 310 */         assignValue((XAttribute)element, value);
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 316 */       String key = (String)keys.get(0);
/* 317 */       List<String> keysTail = keys.subList(1, keys.size());
/*     */       XAttribute attr;
/* 319 */       XAttribute attr; if (element.getAttributes().containsKey(key))
/*     */       {
/*     */ 
/*     */ 
/* 323 */         attr = (XAttribute)element.getAttributes().get(key);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 328 */         attr = ((XFactory)XFactoryRegistry.instance().currentDefault()).createAttributeLiteral(key, "", null);
/*     */         
/* 330 */         element.getAttributes().put(key, attr);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 338 */       assignNestedValuesPrivate(attr, keysTail, value);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/XAbstractNestedAttributeSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */