/*     */ package org.deckfour.xes.info;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import org.deckfour.xes.info.impl.XAttributeNameMapImpl;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XGlobalAttributeNameMap
/*     */   implements XAttributeNameMap
/*     */ {
/*     */   public static final String MAPPING_STANDARD = "EN";
/*     */   public static final String MAPPING_ENGLISH = "EN";
/*     */   public static final String MAPPING_GERMAN = "DE";
/*     */   public static final String MAPPING_DUTCH = "NL";
/*     */   public static final String MAPPING_FRENCH = "FR";
/*     */   public static final String MAPPING_ITALIAN = "IT";
/*     */   public static final String MAPPING_SPANISH = "ES";
/*     */   public static final String MAPPING_PORTUGUESE = "PT";
/* 100 */   private static final XGlobalAttributeNameMap singleton = new XGlobalAttributeNameMap();
/*     */   
/*     */   private HashMap<String, XAttributeNameMapImpl> mappings;
/*     */   
/*     */   private XAttributeNameMapImpl standardMapping;
/*     */   
/*     */   public static XGlobalAttributeNameMap instance()
/*     */   {
/* 108 */     return singleton;
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
/*     */   private XGlobalAttributeNameMap()
/*     */   {
/* 124 */     this.mappings = new HashMap();
/* 125 */     this.standardMapping = new XAttributeNameMapImpl("EN");
/* 126 */     this.mappings.put("EN", this.standardMapping);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<String> getAvailableMappingNames()
/*     */   {
/* 136 */     return Collections.unmodifiableCollection(this.mappings.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<XAttributeNameMap> getAvailableMappings()
/*     */   {
/* 146 */     HashSet<XAttributeNameMap> result = new HashSet();
/* 147 */     result.addAll(this.mappings.values());
/* 148 */     return Collections.unmodifiableCollection(result);
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
/*     */   public XAttributeNameMap getMapping(String name)
/*     */   {
/* 164 */     XAttributeNameMapImpl mapping = (XAttributeNameMapImpl)this.mappings.get(name);
/* 165 */     if (mapping == null) {
/* 166 */       mapping = new XAttributeNameMapImpl(name);
/* 167 */       this.mappings.put(name, mapping);
/*     */     }
/* 169 */     return mapping;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeNameMap getStandardMapping()
/*     */   {
/* 179 */     return this.standardMapping;
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
/*     */   public String mapSafely(XAttribute attribute, XAttributeNameMap mapping)
/*     */   {
/* 196 */     return mapSafely(attribute.getKey(), mapping);
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
/*     */   public String mapSafely(String attributeKey, XAttributeNameMap mapping)
/*     */   {
/* 213 */     String alias = null;
/* 214 */     if (mapping != null)
/*     */     {
/* 216 */       alias = mapping.map(attributeKey);
/*     */     }
/* 218 */     if (alias == null)
/*     */     {
/* 220 */       alias = this.standardMapping.map(attributeKey);
/*     */     }
/* 222 */     if (alias == null)
/*     */     {
/* 224 */       alias = attributeKey;
/*     */     }
/* 226 */     return alias;
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
/*     */   public String mapSafely(XAttribute attribute, String mappingName)
/*     */   {
/* 243 */     return mapSafely(attribute, (XAttributeNameMap)this.mappings.get(mappingName));
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
/*     */   public String mapSafely(String attributeKey, String mappingName)
/*     */   {
/* 260 */     return mapSafely(attributeKey, (XAttributeNameMap)this.mappings.get(mappingName));
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
/*     */   public void registerMapping(String mappingName, String attributeKey, String alias)
/*     */   {
/* 273 */     XAttributeNameMapImpl mapping = (XAttributeNameMapImpl)getMapping(mappingName);
/* 274 */     mapping.registerMapping(attributeKey, alias);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getMappingName()
/*     */   {
/* 281 */     return "EN";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String map(XAttribute attribute)
/*     */   {
/* 288 */     return this.standardMapping.map(attribute);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String map(String attributeKey)
/*     */   {
/* 295 */     return this.standardMapping.map(attributeKey);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 299 */     StringBuilder sb = new StringBuilder();
/* 300 */     sb.append("Global attribute name map.\n\nContained maps:\n\n");
/* 301 */     for (XAttributeNameMapImpl map : this.mappings.values()) {
/* 302 */       sb.append(map.toString());
/* 303 */       sb.append("\n\n");
/*     */     }
/* 305 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/info/XGlobalAttributeNameMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */