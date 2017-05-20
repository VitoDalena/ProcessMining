/*     */ package com.thoughtworks.xstream.mapper;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PackageAliasingMapper
/*     */   extends MapperWrapper
/*     */   implements Serializable
/*     */ {
/*  31 */   private static final Comparator REVERSE = new Comparator()
/*     */   {
/*     */     public int compare(Object o1, Object o2) {
/*  34 */       return ((String)o2).compareTo((String)o1);
/*     */     }
/*     */   };
/*     */   
/*  38 */   private Map packageToName = new TreeMap(REVERSE);
/*  39 */   protected transient Map nameToPackage = new HashMap();
/*     */   
/*     */   public PackageAliasingMapper(Mapper wrapped) {
/*  42 */     super(wrapped);
/*     */   }
/*     */   
/*     */   public void addPackageAlias(String name, String pkg) {
/*  46 */     if ((name.length() > 0) && (name.charAt(name.length() - 1) != '.')) {
/*  47 */       name = name + '.';
/*     */     }
/*  49 */     if ((pkg.length() > 0) && (pkg.charAt(pkg.length() - 1) != '.')) {
/*  50 */       pkg = pkg + '.';
/*     */     }
/*  52 */     this.nameToPackage.put(name, pkg);
/*  53 */     this.packageToName.put(pkg, name);
/*     */   }
/*     */   
/*     */   public String serializedClass(Class type) {
/*  57 */     String className = type.getName();
/*  58 */     int length = className.length();
/*  59 */     int dot = -1;
/*     */     do {
/*  61 */       dot = className.lastIndexOf('.', length);
/*  62 */       String pkg = dot < 0 ? "" : className.substring(0, dot + 1);
/*  63 */       String alias = (String)this.packageToName.get(pkg);
/*  64 */       if (alias != null) {
/*  65 */         return alias + (dot < 0 ? className : className.substring(dot + 1));
/*     */       }
/*  67 */       length = dot - 1;
/*  68 */     } while (dot >= 0);
/*  69 */     return super.serializedClass(type);
/*     */   }
/*     */   
/*     */   public Class realClass(String elementName) {
/*  73 */     int length = elementName.length();
/*  74 */     int dot = -1;
/*     */     do {
/*  76 */       dot = elementName.lastIndexOf('.', length);
/*  77 */       String name = elementName.substring(0, dot) + '.';
/*  78 */       String packageName = (String)this.nameToPackage.get(name);
/*     */       
/*  80 */       if (packageName != null) {
/*  81 */         elementName = packageName + (dot < 0 ? elementName : elementName.substring(dot + 1));
/*     */         
/*  83 */         break;
/*     */       }
/*  85 */       length = dot - 1;
/*  86 */     } while (dot >= 0);
/*     */     
/*  88 */     return super.realClass(elementName);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  92 */     out.writeObject(new HashMap(this.packageToName));
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
/*     */   {
/*  97 */     this.packageToName = new TreeMap(REVERSE);
/*  98 */     this.packageToName.putAll((Map)in.readObject());
/*  99 */     this.nameToPackage = new HashMap();
/* 100 */     for (Iterator iter = this.packageToName.keySet().iterator(); iter.hasNext();) {
/* 101 */       Object type = iter.next();
/* 102 */       this.nameToPackage.put(this.packageToName.get(type), type);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/PackageAliasingMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */