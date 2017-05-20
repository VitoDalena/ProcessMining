/*     */ package org.deckfour.xes.info.impl;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.deckfour.xes.info.XAttributeNameMap;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAttributeNameMapImpl
/*     */   implements XAttributeNameMap
/*     */ {
/*     */   private final String name;
/*     */   private HashMap<String, String> mapping;
/*     */   
/*     */   public XAttributeNameMapImpl(String name)
/*     */   {
/*  69 */     this.name = name;
/*  70 */     this.mapping = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getMappingName()
/*     */   {
/*  77 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String map(XAttribute attribute)
/*     */   {
/*  84 */     return map(attribute.getKey());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String map(String attributeKey)
/*     */   {
/*  91 */     return (String)this.mapping.get(attributeKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void registerMapping(XAttribute attribute, String alias)
/*     */   {
/* 101 */     registerMapping(attribute.getKey(), alias);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void registerMapping(String attributeKey, String alias)
/*     */   {
/* 111 */     this.mapping.put(attributeKey, alias);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 115 */     StringBuilder sb = new StringBuilder();
/* 116 */     sb.append("Attribute name map: ");
/* 117 */     sb.append(this.name);
/* 118 */     for (String key : this.mapping.keySet()) {
/* 119 */       sb.append("\n");
/* 120 */       sb.append(key);
/* 121 */       sb.append(" -> ");
/* 122 */       sb.append((String)this.mapping.get(key));
/*     */     }
/* 124 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/info/impl/XAttributeNameMapImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */