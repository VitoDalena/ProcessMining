/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyToGroupMap
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2228169345475318082L;
/*     */   private Comparable defaultGroup;
/*     */   private List groups;
/*     */   private Map keyToGroupMap;
/*     */   
/*     */   public KeyToGroupMap()
/*     */   {
/*  81 */     this("Default Group");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KeyToGroupMap(Comparable defaultGroup)
/*     */   {
/*  90 */     if (defaultGroup == null) {
/*  91 */       throw new IllegalArgumentException("Null 'defaultGroup' argument.");
/*     */     }
/*  93 */     this.defaultGroup = defaultGroup;
/*  94 */     this.groups = new ArrayList();
/*  95 */     this.keyToGroupMap = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getGroupCount()
/*     */   {
/* 104 */     return this.groups.size() + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getGroups()
/*     */   {
/* 115 */     List result = new ArrayList();
/* 116 */     result.add(this.defaultGroup);
/* 117 */     Iterator iterator = this.groups.iterator();
/* 118 */     while (iterator.hasNext()) {
/* 119 */       Comparable group = (Comparable)iterator.next();
/* 120 */       if (!result.contains(group)) {
/* 121 */         result.add(group);
/*     */       }
/*     */     }
/* 124 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getGroupIndex(Comparable group)
/*     */   {
/* 136 */     int result = this.groups.indexOf(group);
/* 137 */     if (result < 0) {
/* 138 */       if (this.defaultGroup.equals(group)) {
/* 139 */         result = 0;
/*     */       }
/*     */     }
/*     */     else {
/* 143 */       result += 1;
/*     */     }
/* 145 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getGroup(Comparable key)
/*     */   {
/* 157 */     if (key == null) {
/* 158 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 160 */     Comparable result = this.defaultGroup;
/* 161 */     Comparable group = (Comparable)this.keyToGroupMap.get(key);
/* 162 */     if (group != null) {
/* 163 */       result = group;
/*     */     }
/* 165 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mapKeyToGroup(Comparable key, Comparable group)
/*     */   {
/* 176 */     if (key == null) {
/* 177 */       throw new IllegalArgumentException("Null 'key' argument.");
/*     */     }
/* 179 */     Comparable currentGroup = getGroup(key);
/* 180 */     if ((!currentGroup.equals(this.defaultGroup)) && 
/* 181 */       (!currentGroup.equals(group))) {
/* 182 */       int count = getKeyCount(currentGroup);
/* 183 */       if (count == 1) {
/* 184 */         this.groups.remove(currentGroup);
/*     */       }
/*     */     }
/*     */     
/* 188 */     if (group == null) {
/* 189 */       this.keyToGroupMap.remove(key);
/*     */     }
/*     */     else {
/* 192 */       if ((!this.groups.contains(group)) && 
/* 193 */         (!this.defaultGroup.equals(group))) {
/* 194 */         this.groups.add(group);
/*     */       }
/*     */       
/* 197 */       this.keyToGroupMap.put(key, group);
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
/*     */   public int getKeyCount(Comparable group)
/*     */   {
/* 211 */     if (group == null) {
/* 212 */       throw new IllegalArgumentException("Null 'group' argument.");
/*     */     }
/* 214 */     int result = 0;
/* 215 */     Iterator iterator = this.keyToGroupMap.values().iterator();
/* 216 */     while (iterator.hasNext()) {
/* 217 */       Comparable g = (Comparable)iterator.next();
/* 218 */       if (group.equals(g)) {
/* 219 */         result++;
/*     */       }
/*     */     }
/* 222 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 233 */     if (obj == this) {
/* 234 */       return true;
/*     */     }
/* 236 */     if (!(obj instanceof KeyToGroupMap)) {
/* 237 */       return false;
/*     */     }
/* 239 */     KeyToGroupMap that = (KeyToGroupMap)obj;
/* 240 */     if (!ObjectUtilities.equal(this.defaultGroup, that.defaultGroup)) {
/* 241 */       return false;
/*     */     }
/* 243 */     if (!this.keyToGroupMap.equals(that.keyToGroupMap)) {
/* 244 */       return false;
/*     */     }
/* 246 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 258 */     KeyToGroupMap result = (KeyToGroupMap)super.clone();
/* 259 */     result.defaultGroup = ((Comparable)clone(this.defaultGroup));
/*     */     
/* 261 */     result.groups = ((List)clone(this.groups));
/* 262 */     result.keyToGroupMap = ((Map)clone(this.keyToGroupMap));
/* 263 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Object clone(Object object)
/*     */   {
/* 274 */     if (object == null) {
/* 275 */       return null;
/*     */     }
/* 277 */     Class c = object.getClass();
/* 278 */     Object result = null;
/*     */     try {
/* 280 */       Method m = c.getMethod("clone", (Class[])null);
/* 281 */       if (Modifier.isPublic(m.getModifiers())) {
/*     */         try {
/* 283 */           result = m.invoke(object, (Object[])null);
/*     */         }
/*     */         catch (Exception e) {
/* 286 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (NoSuchMethodException e) {
/* 291 */       result = object;
/*     */     }
/* 293 */     return result;
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
/*     */   private static Collection clone(Collection list)
/*     */     throws CloneNotSupportedException
/*     */   {
/* 307 */     Collection result = null;
/* 308 */     if (list != null) {
/*     */       try {
/* 310 */         List clone = (List)list.getClass().newInstance();
/* 311 */         Iterator iterator = list.iterator();
/* 312 */         while (iterator.hasNext()) {
/* 313 */           clone.add(clone(iterator.next()));
/*     */         }
/* 315 */         result = clone;
/*     */       }
/*     */       catch (Exception e) {
/* 318 */         throw new CloneNotSupportedException("Exception.");
/*     */       }
/*     */     }
/* 321 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/KeyToGroupMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */