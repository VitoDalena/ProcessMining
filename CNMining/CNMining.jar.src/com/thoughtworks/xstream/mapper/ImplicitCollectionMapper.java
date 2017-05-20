/*     */ package com.thoughtworks.xstream.mapper;
/*     */ 
/*     */ import com.thoughtworks.xstream.InitializationException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ public class ImplicitCollectionMapper
/*     */   extends MapperWrapper
/*     */ {
/*     */   public ImplicitCollectionMapper(Mapper wrapped)
/*     */   {
/*  26 */     super(wrapped);
/*     */   }
/*     */   
/*     */ 
/*  30 */   private final Map classNameToMapper = new HashMap();
/*     */   
/*     */   private ImplicitCollectionMapperForClass getMapper(Class definedIn) {
/*  33 */     while (definedIn != null) {
/*  34 */       ImplicitCollectionMapperForClass mapper = (ImplicitCollectionMapperForClass)this.classNameToMapper.get(definedIn);
/*     */       
/*  36 */       if (mapper != null) {
/*  37 */         return mapper;
/*     */       }
/*  39 */       definedIn = definedIn.getSuperclass();
/*     */     }
/*  41 */     return null;
/*     */   }
/*     */   
/*     */   private ImplicitCollectionMapperForClass getOrCreateMapper(Class definedIn) {
/*  45 */     ImplicitCollectionMapperForClass mapper = getMapper(definedIn);
/*  46 */     if (mapper == null) {
/*  47 */       mapper = new ImplicitCollectionMapperForClass(null);
/*  48 */       this.classNameToMapper.put(definedIn, mapper);
/*     */     }
/*  50 */     return mapper;
/*     */   }
/*     */   
/*     */   public String getFieldNameForItemTypeAndName(Class definedIn, Class itemType, String itemFieldName)
/*     */   {
/*  55 */     ImplicitCollectionMapperForClass mapper = getMapper(definedIn);
/*  56 */     if (mapper != null) {
/*  57 */       return mapper.getFieldNameForItemTypeAndName(itemType, itemFieldName);
/*     */     }
/*  59 */     return null;
/*     */   }
/*     */   
/*     */   public Class getItemTypeForItemFieldName(Class definedIn, String itemFieldName)
/*     */   {
/*  64 */     ImplicitCollectionMapperForClass mapper = getMapper(definedIn);
/*  65 */     if (mapper != null) {
/*  66 */       return mapper.getItemTypeForItemFieldName(itemFieldName);
/*     */     }
/*  68 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public Mapper.ImplicitCollectionMapping getImplicitCollectionDefForFieldName(Class itemType, String fieldName)
/*     */   {
/*  74 */     ImplicitCollectionMapperForClass mapper = getMapper(itemType);
/*  75 */     if (mapper != null) {
/*  76 */       return mapper.getImplicitCollectionDefForFieldName(fieldName);
/*     */     }
/*  78 */     return null;
/*     */   }
/*     */   
/*     */   public void add(Class definedIn, String fieldName, Class itemType)
/*     */   {
/*  83 */     add(definedIn, fieldName, null, itemType);
/*     */   }
/*     */   
/*     */   public void add(Class definedIn, String fieldName, String itemFieldName, Class itemType) {
/*  87 */     Field field = null;
/*  88 */     while (definedIn != Object.class) {
/*     */       try {
/*  90 */         field = definedIn.getDeclaredField(fieldName);
/*     */       }
/*     */       catch (SecurityException e) {
/*  93 */         throw new InitializationException("Access denied for field with implicit collection", e);
/*     */       }
/*     */       catch (NoSuchFieldException e) {
/*  96 */         definedIn = definedIn.getSuperclass();
/*     */       }
/*     */     }
/*  99 */     if (field == null) {
/* 100 */       throw new InitializationException("No field \"" + fieldName + "\" for implicit collection");
/*     */     }
/*     */     
/* 103 */     if (!Collection.class.isAssignableFrom(field.getType())) {
/* 104 */       throw new InitializationException("Field \"" + fieldName + "\" declares no collection");
/*     */     }
/*     */     
/*     */ 
/* 108 */     ImplicitCollectionMapperForClass mapper = getOrCreateMapper(definedIn);
/* 109 */     mapper.add(new ImplicitCollectionMappingImpl(fieldName, itemType, itemFieldName));
/*     */   }
/*     */   
/* 112 */   private static class ImplicitCollectionMapperForClass { ImplicitCollectionMapperForClass(ImplicitCollectionMapper.1 x0) { this(); }
/*     */     
/* 114 */     private Map namedItemTypeToDef = new HashMap();
/*     */     
/* 116 */     private Map itemFieldNameToDef = new HashMap();
/*     */     
/* 118 */     private Map fieldNameToDef = new HashMap();
/*     */     
/*     */     public String getFieldNameForItemTypeAndName(Class itemType, String itemFieldName) {
/* 121 */       ImplicitCollectionMapper.ImplicitCollectionMappingImpl unnamed = null;
/* 122 */       for (Iterator iterator = this.namedItemTypeToDef.keySet().iterator(); iterator.hasNext();) {
/* 123 */         ImplicitCollectionMapper.NamedItemType itemTypeForFieldName = (ImplicitCollectionMapper.NamedItemType)iterator.next();
/* 124 */         ImplicitCollectionMapper.ImplicitCollectionMappingImpl def = (ImplicitCollectionMapper.ImplicitCollectionMappingImpl)this.namedItemTypeToDef.get(itemTypeForFieldName);
/*     */         
/* 126 */         if (itemType == Mapper.Null.class) {
/* 127 */           unnamed = def;
/*     */         }
/* 129 */         else if (itemTypeForFieldName.itemType.isAssignableFrom(itemType)) {
/* 130 */           if (def.getItemFieldName() != null) {
/* 131 */             if (def.getItemFieldName().equals(itemFieldName)) {
/* 132 */               return def.getFieldName();
/*     */             }
/*     */           } else {
/* 135 */             unnamed = def;
/* 136 */             if (itemFieldName == null) {
/*     */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 142 */       return unnamed != null ? unnamed.getFieldName() : null;
/*     */     }
/*     */     
/*     */     public Class getItemTypeForItemFieldName(String itemFieldName) {
/* 146 */       ImplicitCollectionMapper.ImplicitCollectionMappingImpl def = getImplicitCollectionDefByItemFieldName(itemFieldName);
/* 147 */       if (def != null) {
/* 148 */         return def.getItemType();
/*     */       }
/* 150 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     private ImplicitCollectionMapper.ImplicitCollectionMappingImpl getImplicitCollectionDefByItemFieldName(String itemFieldName)
/*     */     {
/* 156 */       if (itemFieldName == null) {
/* 157 */         return null;
/*     */       }
/* 159 */       return (ImplicitCollectionMapper.ImplicitCollectionMappingImpl)this.itemFieldNameToDef.get(itemFieldName);
/*     */     }
/*     */     
/*     */ 
/*     */     public ImplicitCollectionMapper.ImplicitCollectionMappingImpl getImplicitCollectionDefByFieldName(String fieldName)
/*     */     {
/* 165 */       return (ImplicitCollectionMapper.ImplicitCollectionMappingImpl)this.fieldNameToDef.get(fieldName);
/*     */     }
/*     */     
/*     */     public Mapper.ImplicitCollectionMapping getImplicitCollectionDefForFieldName(String fieldName) {
/* 169 */       return (Mapper.ImplicitCollectionMapping)this.fieldNameToDef.get(fieldName);
/*     */     }
/*     */     
/*     */     public void add(ImplicitCollectionMapper.ImplicitCollectionMappingImpl def) {
/* 173 */       this.fieldNameToDef.put(def.getFieldName(), def);
/* 174 */       this.namedItemTypeToDef.put(def.createNamedItemType(), def);
/* 175 */       if (def.getItemFieldName() != null)
/* 176 */         this.itemFieldNameToDef.put(def.getItemFieldName(), def);
/*     */     }
/*     */     
/*     */     private ImplicitCollectionMapperForClass() {}
/*     */   }
/*     */   
/*     */   private static class ImplicitCollectionMappingImpl implements Mapper.ImplicitCollectionMapping {
/*     */     private String fieldName;
/*     */     private String itemFieldName;
/*     */     private Class itemType;
/*     */     
/*     */     ImplicitCollectionMappingImpl(String fieldName, Class itemType, String itemFieldName) {
/* 188 */       this.fieldName = fieldName;
/* 189 */       this.itemFieldName = itemFieldName;
/* 190 */       this.itemType = (itemType == null ? Object.class : itemType);
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 194 */       if ((obj instanceof ImplicitCollectionMappingImpl)) {
/* 195 */         ImplicitCollectionMappingImpl b = (ImplicitCollectionMappingImpl)obj;
/* 196 */         return (this.fieldName.equals(b.fieldName)) && (isEquals(this.itemFieldName, b.itemFieldName));
/*     */       }
/*     */       
/* 199 */       return false;
/*     */     }
/*     */     
/*     */     public ImplicitCollectionMapper.NamedItemType createNamedItemType()
/*     */     {
/* 204 */       return new ImplicitCollectionMapper.NamedItemType(this.itemType, this.itemFieldName);
/*     */     }
/*     */     
/*     */     private static boolean isEquals(Object a, Object b) {
/* 208 */       if (a == null) {
/* 209 */         return b == null;
/*     */       }
/* 211 */       return a.equals(b);
/*     */     }
/*     */     
/*     */     public int hashCode()
/*     */     {
/* 216 */       int hash = this.fieldName.hashCode();
/* 217 */       if (this.itemFieldName != null) {
/* 218 */         hash += (this.itemFieldName.hashCode() << 7);
/*     */       }
/* 220 */       return hash;
/*     */     }
/*     */     
/*     */     public String getFieldName() {
/* 224 */       return this.fieldName;
/*     */     }
/*     */     
/*     */     public String getItemFieldName() {
/* 228 */       return this.itemFieldName;
/*     */     }
/*     */     
/*     */     public Class getItemType() {
/* 232 */       return this.itemType;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class NamedItemType {
/*     */     Class itemType;
/*     */     String itemFieldName;
/*     */     
/*     */     NamedItemType(Class itemType, String itemFieldName) {
/* 241 */       this.itemType = itemType;
/* 242 */       this.itemFieldName = itemFieldName;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 246 */       if ((obj instanceof NamedItemType)) {
/* 247 */         NamedItemType b = (NamedItemType)obj;
/* 248 */         return (this.itemType.equals(b.itemType)) && (isEquals(this.itemFieldName, b.itemFieldName));
/*     */       }
/* 250 */       return false;
/*     */     }
/*     */     
/*     */     private static boolean isEquals(Object a, Object b)
/*     */     {
/* 255 */       if (a == null) {
/* 256 */         return b == null;
/*     */       }
/* 258 */       return a.equals(b);
/*     */     }
/*     */     
/*     */     public int hashCode()
/*     */     {
/* 263 */       int hash = this.itemType.hashCode() << 7;
/* 264 */       if (this.itemFieldName != null) {
/* 265 */         hash += this.itemFieldName.hashCode();
/*     */       }
/* 267 */       return hash;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/ImplicitCollectionMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */