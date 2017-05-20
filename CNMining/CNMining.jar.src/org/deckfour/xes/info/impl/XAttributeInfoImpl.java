/*     */ package org.deckfour.xes.info.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.info.XAttributeInfo;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.util.XAttributeUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAttributeInfoImpl
/*     */   implements XAttributeInfo
/*     */ {
/*     */   private Map<String, XAttribute> keyMap;
/*     */   private Map<Class<? extends XAttribute>, Set<XAttribute>> typeMap;
/*     */   private Map<XExtension, Set<XAttribute>> extensionMap;
/*     */   private Set<XAttribute> noExtensionSet;
/*     */   private Map<String, Integer> frequencies;
/*     */   private int totalFrequency;
/*     */   
/*     */   public XAttributeInfoImpl()
/*     */   {
/*  93 */     this.keyMap = new HashMap();
/*  94 */     this.frequencies = new HashMap();
/*  95 */     this.typeMap = new HashMap();
/*  96 */     this.extensionMap = new HashMap();
/*  97 */     this.noExtensionSet = new HashSet();
/*  98 */     this.totalFrequency = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<XAttribute> getAttributes()
/*     */   {
/* 105 */     return Collections.unmodifiableCollection(this.keyMap.values());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<String> getAttributeKeys()
/*     */   {
/* 112 */     return Collections.unmodifiableCollection(this.keyMap.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getFrequency(String key)
/*     */   {
/* 119 */     return ((Integer)this.frequencies.get(key)).intValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getFrequency(XAttribute attribute)
/*     */   {
/* 126 */     return getFrequency(attribute.getKey());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getRelativeFrequency(String key)
/*     */   {
/* 133 */     return ((Integer)this.frequencies.get(key)).intValue() / this.totalFrequency;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getRelativeFrequency(XAttribute attribute)
/*     */   {
/* 140 */     return getRelativeFrequency(attribute.getKey());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<XAttribute> getAttributesForType(Class<? extends XAttribute> type)
/*     */   {
/* 147 */     Set<XAttribute> typeSet = (Set)this.typeMap.get(type);
/* 148 */     if (typeSet == null) {
/* 149 */       typeSet = new HashSet(0);
/*     */     }
/* 151 */     return Collections.unmodifiableCollection(typeSet);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<String> getKeysForType(Class<? extends XAttribute> type)
/*     */   {
/* 158 */     Collection<XAttribute> typeCollection = getAttributesForType(type);
/* 159 */     Set<String> keySet = new HashSet();
/* 160 */     for (XAttribute attribute : typeCollection) {
/* 161 */       keySet.add(attribute.getKey());
/*     */     }
/* 163 */     return Collections.unmodifiableCollection(keySet);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<XAttribute> getAttributesForExtension(XExtension extension)
/*     */   {
/* 170 */     if (extension == null) {
/* 171 */       return getAttributesWithoutExtension();
/*     */     }
/* 173 */     Set<XAttribute> extensionSet = (Set)this.extensionMap.get(extension);
/* 174 */     if (extensionSet == null) {
/* 175 */       extensionSet = new HashSet(0);
/*     */     }
/* 177 */     return Collections.unmodifiableCollection(extensionSet);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<String> getKeysForExtension(XExtension extension)
/*     */   {
/* 185 */     Collection<XAttribute> extensionCollection = getAttributesForExtension(extension);
/* 186 */     Set<String> keySet = new HashSet();
/* 187 */     for (XAttribute attribute : extensionCollection) {
/* 188 */       keySet.add(attribute.getKey());
/*     */     }
/* 190 */     return Collections.unmodifiableCollection(keySet);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<XAttribute> getAttributesWithoutExtension()
/*     */   {
/* 197 */     return Collections.unmodifiableCollection(this.noExtensionSet);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<String> getKeysWithoutExtension()
/*     */   {
/* 204 */     return getKeysForExtension(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void register(XAttribute attribute)
/*     */   {
/* 213 */     if (!this.keyMap.containsKey(attribute.getKey()))
/*     */     {
/* 215 */       XAttribute prototype = XAttributeUtils.derivePrototype(attribute);
/*     */       
/* 217 */       this.keyMap.put(attribute.getKey(), prototype);
/*     */       
/* 219 */       this.frequencies.put(attribute.getKey(), Integer.valueOf(1));
/*     */       
/* 221 */       Set<XAttribute> typeSet = (Set)this.typeMap.get(XAttributeUtils.getType(prototype));
/* 222 */       if (typeSet == null) {
/* 223 */         typeSet = new HashSet();
/* 224 */         this.typeMap.put(XAttributeUtils.getType(prototype), typeSet);
/*     */       }
/* 226 */       typeSet.add(prototype);
/*     */       
/* 228 */       if (attribute.getExtension() == null)
/*     */       {
/* 230 */         this.noExtensionSet.add(prototype);
/*     */       }
/*     */       else {
/* 233 */         Set<XAttribute> extensionSet = (Set)this.extensionMap.get(attribute.getExtension());
/* 234 */         if (extensionSet == null) {
/* 235 */           extensionSet = new HashSet();
/* 236 */           this.extensionMap.put(attribute.getExtension(), extensionSet);
/*     */         }
/* 238 */         extensionSet.add(prototype);
/*     */       }
/*     */     }
/*     */     else {
/* 242 */       this.frequencies.put(attribute.getKey(), Integer.valueOf(((Integer)this.frequencies.get(attribute.getKey())).intValue() + 1));
/*     */     }
/*     */     
/* 245 */     this.totalFrequency += 1;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/info/impl/XAttributeInfoImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */