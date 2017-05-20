/*     */ package org.deckfour.uitopia.ui.conf;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigurationSet
/*     */   extends HashMap<String, String>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final String name;
/*     */   private HashMap<String, ConfigurationSet> children;
/*     */   
/*     */   public ConfigurationSet(String name)
/*     */   {
/*  52 */     this.name = name;
/*  53 */     this.children = new HashMap();
/*     */   }
/*     */   
/*     */   public Collection<ConfigurationSet> getChildren() {
/*  57 */     return Collections.unmodifiableCollection(this.children.values());
/*     */   }
/*     */   
/*     */   public ConfigurationSet getChild(String name) {
/*  61 */     ConfigurationSet child = (ConfigurationSet)this.children.get(name);
/*  62 */     if (child == null) {
/*  63 */       child = new ConfigurationSet(name);
/*  64 */       this.children.put(name, child);
/*     */     }
/*  66 */     return child;
/*     */   }
/*     */   
/*     */   public void addChild(ConfigurationSet child) {
/*  70 */     this.children.put(child.name(), child);
/*     */   }
/*     */   
/*     */   public void set(String key, String value) {
/*  74 */     put(key, value);
/*     */   }
/*     */   
/*     */   public String get(String key, String defaultValue) {
/*  78 */     String value = (String)get(key);
/*  79 */     if (value == null) {
/*  80 */       return defaultValue;
/*     */     }
/*  82 */     return value;
/*     */   }
/*     */   
/*     */   public void setInteger(String key, int value)
/*     */   {
/*  87 */     put(key, Integer.toString(value));
/*     */   }
/*     */   
/*     */   public int getInteger(String key) {
/*  91 */     return getInteger(key, 0);
/*     */   }
/*     */   
/*     */   public int getInteger(String key, int defaultValue) {
/*  95 */     String value = (String)get(key);
/*  96 */     if (value == null) {
/*  97 */       return defaultValue;
/*     */     }
/*  99 */     return Integer.parseInt(value);
/*     */   }
/*     */   
/*     */   public void setDouble(String key, double value)
/*     */   {
/* 104 */     put(key, Double.toString(value));
/*     */   }
/*     */   
/*     */   public double getDouble(String key) {
/* 108 */     return getDouble(key, 0.0D);
/*     */   }
/*     */   
/*     */   public double getDouble(String key, double defaultValue) {
/* 112 */     String value = (String)get(key);
/* 113 */     if (value == null) {
/* 114 */       return defaultValue;
/*     */     }
/* 116 */     return Double.parseDouble(value);
/*     */   }
/*     */   
/*     */   public String name()
/*     */   {
/* 121 */     return this.name;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 125 */     return this.name;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 129 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 133 */     if ((o instanceof ConfigurationSet)) {
/* 134 */       ConfigurationSet other = (ConfigurationSet)o;
/* 135 */       return other.name.equals(this.name);
/*     */     }
/* 137 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/conf/ConfigurationSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */