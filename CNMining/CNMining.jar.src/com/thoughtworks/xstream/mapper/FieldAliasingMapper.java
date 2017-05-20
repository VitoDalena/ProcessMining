/*    */ package com.thoughtworks.xstream.mapper;
/*    */ 
/*    */ import com.thoughtworks.xstream.core.util.FastField;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FieldAliasingMapper
/*    */   extends MapperWrapper
/*    */ {
/* 29 */   protected final Map fieldToAliasMap = new HashMap();
/* 30 */   protected final Map aliasToFieldMap = new HashMap();
/* 31 */   protected final Set fieldsToOmit = new HashSet();
/*    */   
/*    */   public FieldAliasingMapper(Mapper wrapped) {
/* 34 */     super(wrapped);
/*    */   }
/*    */   
/*    */   public void addFieldAlias(String alias, Class type, String fieldName) {
/* 38 */     this.fieldToAliasMap.put(key(type, fieldName), alias);
/* 39 */     this.aliasToFieldMap.put(key(type, alias), fieldName);
/*    */   }
/*    */   
/*    */   private Object key(Class type, String name) {
/* 43 */     return new FastField(type, name);
/*    */   }
/*    */   
/*    */   public String serializedMember(Class type, String memberName) {
/* 47 */     String alias = getMember(type, memberName, this.fieldToAliasMap);
/* 48 */     if (alias == null) {
/* 49 */       return super.serializedMember(type, memberName);
/*    */     }
/* 51 */     return alias;
/*    */   }
/*    */   
/*    */   public String realMember(Class type, String serialized)
/*    */   {
/* 56 */     String real = getMember(type, serialized, this.aliasToFieldMap);
/* 57 */     if (real == null) {
/* 58 */       return super.realMember(type, serialized);
/*    */     }
/* 60 */     return real;
/*    */   }
/*    */   
/*    */   private String getMember(Class type, String name, Map map)
/*    */   {
/* 65 */     String member = null;
/* 66 */     for (Class declaringType = type; (member == null) && (declaringType != Object.class); declaringType = declaringType.getSuperclass()) {
/* 67 */       member = (String)map.get(key(declaringType, name));
/*    */     }
/* 69 */     return member;
/*    */   }
/*    */   
/*    */   public boolean shouldSerializeMember(Class definedIn, String fieldName) {
/* 73 */     return !this.fieldsToOmit.contains(key(definedIn, fieldName));
/*    */   }
/*    */   
/*    */   public void omitField(Class definedIn, String fieldName) {
/* 77 */     this.fieldsToOmit.add(key(definedIn, fieldName));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/FieldAliasingMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */