/*     */ package com.thoughtworks.xstream.converters.reflection;
/*     */ 
/*     */ import com.thoughtworks.xstream.core.JVM;
/*     */ import com.thoughtworks.xstream.core.util.OrderRetainingMap;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FieldDictionary
/*     */ {
/*     */   private transient Map keyedByFieldNameCache;
/*     */   private transient Map keyedByFieldKeyCache;
/*     */   private final FieldKeySorter sorter;
/*     */   
/*     */   public FieldDictionary()
/*     */   {
/*  42 */     this(new ImmutableFieldKeySorter());
/*     */   }
/*     */   
/*     */   public FieldDictionary(FieldKeySorter sorter) {
/*  46 */     this.sorter = sorter;
/*  47 */     init();
/*     */   }
/*     */   
/*     */   private void init() {
/*  51 */     this.keyedByFieldNameCache = new HashMap();
/*  52 */     this.keyedByFieldKeyCache = new HashMap();
/*  53 */     this.keyedByFieldNameCache.put(Object.class, Collections.EMPTY_MAP);
/*  54 */     this.keyedByFieldKeyCache.put(Object.class, Collections.EMPTY_MAP);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Iterator serializableFieldsFor(Class cls)
/*     */   {
/*  65 */     return fieldsFor(cls);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator fieldsFor(Class cls)
/*     */   {
/*  75 */     return buildMap(cls, true).values().iterator();
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
/*     */   public Field field(Class cls, String name, Class definedIn)
/*     */   {
/*  91 */     Field field = fieldOrNull(cls, name, definedIn);
/*  92 */     if (field == null) {
/*  93 */       throw new ObjectAccessException("No such field " + cls.getName() + "." + name);
/*     */     }
/*  95 */     return field;
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
/*     */   public Field fieldOrNull(Class cls, String name, Class definedIn)
/*     */   {
/* 112 */     Map fields = buildMap(cls, definedIn != null);
/* 113 */     Field field = (Field)fields.get(definedIn != null ? new FieldKey(name, definedIn, 0) : name);
/*     */     
/*     */ 
/* 116 */     return field;
/*     */   }
/*     */   
/*     */   private Map buildMap(Class type, boolean tupleKeyed)
/*     */   {
/* 121 */     Class cls = type;
/* 122 */     Map result; synchronized (this) { Map result;
/* 123 */       if (!this.keyedByFieldNameCache.containsKey(type)) {
/* 124 */         List superClasses = new ArrayList();
/* 125 */         while (!Object.class.equals(cls)) {
/* 126 */           superClasses.add(0, cls);
/* 127 */           cls = cls.getSuperclass();
/*     */         }
/* 129 */         Map lastKeyedByFieldName = Collections.EMPTY_MAP;
/* 130 */         Map lastKeyedByFieldKey = Collections.EMPTY_MAP;
/* 131 */         for (Iterator iter = superClasses.iterator(); iter.hasNext();) {
/* 132 */           cls = (Class)iter.next();
/* 133 */           if (!this.keyedByFieldNameCache.containsKey(cls)) {
/* 134 */             Map keyedByFieldName = new HashMap(lastKeyedByFieldName);
/* 135 */             Map keyedByFieldKey = new OrderRetainingMap(lastKeyedByFieldKey);
/* 136 */             Field[] fields = cls.getDeclaredFields();
/* 137 */             int i; if (JVM.reverseFieldDefinition()) {
/* 138 */               for (i = fields.length >> 1; i-- > 0;) {
/* 139 */                 int idx = fields.length - i - 1;
/* 140 */                 Field field = fields[i];
/* 141 */                 fields[i] = fields[idx];
/* 142 */                 fields[idx] = field;
/*     */               }
/*     */             }
/* 145 */             for (int i = 0; i < fields.length; i++) {
/* 146 */               Field field = fields[i];
/* 147 */               if (!field.isAccessible()) {
/* 148 */                 field.setAccessible(true);
/*     */               }
/* 150 */               FieldKey fieldKey = new FieldKey(field.getName(), field.getDeclaringClass(), i);
/*     */               
/* 152 */               Field existent = (Field)keyedByFieldName.get(field.getName());
/* 153 */               if ((existent == null) || ((existent.getModifiers() & 0x8) != 0) || ((existent != null) && ((field.getModifiers() & 0x8) == 0)))
/*     */               {
/*     */ 
/*     */ 
/*     */ 
/* 158 */                 keyedByFieldName.put(field.getName(), field);
/*     */               }
/* 160 */               keyedByFieldKey.put(fieldKey, field);
/*     */             }
/* 162 */             Map sortedFieldKeys = this.sorter.sort(type, keyedByFieldKey);
/* 163 */             this.keyedByFieldNameCache.put(cls, keyedByFieldName);
/* 164 */             this.keyedByFieldKeyCache.put(cls, sortedFieldKeys);
/* 165 */             lastKeyedByFieldName = keyedByFieldName;
/* 166 */             lastKeyedByFieldKey = sortedFieldKeys;
/*     */           } else {
/* 168 */             lastKeyedByFieldName = (Map)this.keyedByFieldNameCache.get(cls);
/* 169 */             lastKeyedByFieldKey = (Map)this.keyedByFieldKeyCache.get(cls);
/*     */           }
/*     */         }
/* 172 */         result = tupleKeyed ? lastKeyedByFieldKey : lastKeyedByFieldName;
/*     */       } else {
/* 174 */         result = (Map)(tupleKeyed ? this.keyedByFieldKeyCache.get(type) : this.keyedByFieldNameCache.get(type));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 179 */     return result;
/*     */   }
/*     */   
/*     */   protected Object readResolve() {
/* 183 */     init();
/* 184 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/FieldDictionary.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */