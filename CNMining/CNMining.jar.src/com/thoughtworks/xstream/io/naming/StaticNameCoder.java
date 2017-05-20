/*     */ package com.thoughtworks.xstream.io.naming;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StaticNameCoder
/*     */   implements NameCoder
/*     */ {
/*     */   private final Map java2Node;
/*     */   private final Map java2Attribute;
/*     */   private transient Map node2Java;
/*     */   private transient Map attribute2Java;
/*     */   
/*     */   public StaticNameCoder(Map java2Node, Map java2Attribute)
/*     */   {
/*  46 */     this.java2Node = new HashMap(java2Node);
/*  47 */     if ((java2Node == java2Attribute) || (java2Attribute == null)) {
/*  48 */       this.java2Attribute = this.java2Node;
/*     */     } else {
/*  50 */       this.java2Attribute = new HashMap(java2Attribute);
/*     */     }
/*  52 */     readResolve();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String decodeAttribute(String attributeName)
/*     */   {
/*  59 */     String name = (String)this.attribute2Java.get(attributeName);
/*  60 */     return name == null ? attributeName : name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String decodeNode(String nodeName)
/*     */   {
/*  67 */     String name = (String)this.node2Java.get(nodeName);
/*  68 */     return name == null ? nodeName : name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String encodeAttribute(String name)
/*     */   {
/*  75 */     String friendlyName = (String)this.java2Attribute.get(name);
/*  76 */     return friendlyName == null ? name : friendlyName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String encodeNode(String name)
/*     */   {
/*  83 */     String friendlyName = (String)this.java2Node.get(name);
/*  84 */     return friendlyName == null ? name : friendlyName;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*  88 */     this.node2Java = invertMap(this.java2Node);
/*  89 */     if (this.java2Node == this.java2Attribute) {
/*  90 */       this.attribute2Java = this.node2Java;
/*     */     } else {
/*  92 */       this.attribute2Java = invertMap(this.java2Attribute);
/*     */     }
/*  94 */     return this;
/*     */   }
/*     */   
/*     */   private Map invertMap(Map map) {
/*  98 */     Map inverseMap = new HashMap(map.size());
/*  99 */     for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
/* 100 */       Map.Entry entry = (Map.Entry)iter.next();
/* 101 */       inverseMap.put((String)entry.getValue(), (String)entry.getKey());
/*     */     }
/* 103 */     return inverseMap;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/naming/StaticNameCoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */