/*     */ package com.thoughtworks.xstream.mapper;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClassAliasingMapper
/*     */   extends MapperWrapper
/*     */ {
/*  26 */   private final Map typeToName = new HashMap();
/*  27 */   private final Map classToName = new HashMap();
/*  28 */   private transient Map nameToType = new HashMap();
/*     */   
/*     */   public ClassAliasingMapper(Mapper wrapped) {
/*  31 */     super(wrapped);
/*     */   }
/*     */   
/*     */   public void addClassAlias(String name, Class type) {
/*  35 */     this.nameToType.put(name, type.getName());
/*  36 */     this.classToName.put(type.getName(), name);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public void addClassAttributeAlias(String name, Class type) {
/*  43 */     addClassAlias(name, type);
/*     */   }
/*     */   
/*     */   public void addTypeAlias(String name, Class type) {
/*  47 */     this.nameToType.put(name, type.getName());
/*  48 */     this.typeToName.put(type, name);
/*     */   }
/*     */   
/*     */   public String serializedClass(Class type) {
/*  52 */     String alias = (String)this.classToName.get(type.getName());
/*  53 */     if (alias != null) {
/*  54 */       return alias;
/*     */     }
/*  56 */     for (Iterator iter = this.typeToName.keySet().iterator(); iter.hasNext();) {
/*  57 */       Class compatibleType = (Class)iter.next();
/*  58 */       if (compatibleType.isAssignableFrom(type)) {
/*  59 */         return (String)this.typeToName.get(compatibleType);
/*     */       }
/*     */     }
/*  62 */     return super.serializedClass(type);
/*     */   }
/*     */   
/*     */   public Class realClass(String elementName)
/*     */   {
/*  67 */     String mappedName = (String)this.nameToType.get(elementName);
/*     */     
/*  69 */     if (mappedName != null) {
/*  70 */       Class type = primitiveClassNamed(mappedName);
/*  71 */       if (type != null) {
/*  72 */         return type;
/*     */       }
/*  74 */       elementName = mappedName;
/*     */     }
/*     */     
/*  77 */     return super.realClass(elementName);
/*     */   }
/*     */   
/*     */   public boolean itemTypeAsAttribute(Class clazz) {
/*  81 */     return this.classToName.containsKey(clazz);
/*     */   }
/*     */   
/*     */   public boolean aliasIsAttribute(String name) {
/*  85 */     return this.nameToType.containsKey(name);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*  89 */     this.nameToType = new HashMap();
/*  90 */     for (Iterator iter = this.classToName.keySet().iterator(); iter.hasNext();) {
/*  91 */       Object type = iter.next();
/*  92 */       this.nameToType.put(this.classToName.get(type), type);
/*     */     }
/*  94 */     for (Iterator iter = this.typeToName.keySet().iterator(); iter.hasNext();) {
/*  95 */       Class type = (Class)iter.next();
/*  96 */       this.nameToType.put(this.typeToName.get(type), type.getName());
/*     */     }
/*  98 */     return this;
/*     */   }
/*     */   
/*     */   private Class primitiveClassNamed(String name) {
/* 102 */     return name.equals("double") ? Double.TYPE : name.equals("float") ? Float.TYPE : name.equals("long") ? Long.TYPE : name.equals("int") ? Integer.TYPE : name.equals("short") ? Short.TYPE : name.equals("char") ? Character.TYPE : name.equals("byte") ? Byte.TYPE : name.equals("boolean") ? Boolean.TYPE : name.equals("void") ? Void.TYPE : null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/ClassAliasingMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */