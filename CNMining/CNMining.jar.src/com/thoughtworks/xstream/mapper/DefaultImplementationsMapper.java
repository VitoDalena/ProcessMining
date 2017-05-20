/*    */ package com.thoughtworks.xstream.mapper;
/*    */ 
/*    */ import com.thoughtworks.xstream.InitializationException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
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
/*    */ 
/*    */ public class DefaultImplementationsMapper
/*    */   extends MapperWrapper
/*    */ {
/* 30 */   private final Map typeToImpl = new HashMap();
/* 31 */   private transient Map implToType = new HashMap();
/*    */   
/*    */   public DefaultImplementationsMapper(Mapper wrapped) {
/* 34 */     super(wrapped);
/* 35 */     addDefaults();
/*    */   }
/*    */   
/*    */   protected void addDefaults()
/*    */   {
/* 40 */     addDefaultImplementation(null, Mapper.Null.class);
/*    */     
/* 42 */     addDefaultImplementation(Boolean.class, Boolean.TYPE);
/* 43 */     addDefaultImplementation(Character.class, Character.TYPE);
/* 44 */     addDefaultImplementation(Integer.class, Integer.TYPE);
/* 45 */     addDefaultImplementation(Float.class, Float.TYPE);
/* 46 */     addDefaultImplementation(Double.class, Double.TYPE);
/* 47 */     addDefaultImplementation(Short.class, Short.TYPE);
/* 48 */     addDefaultImplementation(Byte.class, Byte.TYPE);
/* 49 */     addDefaultImplementation(Long.class, Long.TYPE);
/*    */   }
/*    */   
/*    */   public void addDefaultImplementation(Class defaultImplementation, Class ofType) {
/* 53 */     if ((defaultImplementation != null) && (defaultImplementation.isInterface())) {
/* 54 */       throw new InitializationException("Default implementation is not a concrete class: " + defaultImplementation.getName());
/*    */     }
/*    */     
/*    */ 
/* 58 */     this.typeToImpl.put(ofType, defaultImplementation);
/* 59 */     this.implToType.put(defaultImplementation, ofType);
/*    */   }
/*    */   
/*    */   public String serializedClass(Class type) {
/* 63 */     Class baseType = (Class)this.implToType.get(type);
/* 64 */     return baseType == null ? super.serializedClass(type) : super.serializedClass(baseType);
/*    */   }
/*    */   
/*    */   public Class defaultImplementationOf(Class type) {
/* 68 */     if (this.typeToImpl.containsKey(type)) {
/* 69 */       return (Class)this.typeToImpl.get(type);
/*    */     }
/* 71 */     return super.defaultImplementationOf(type);
/*    */   }
/*    */   
/*    */   private Object readResolve()
/*    */   {
/* 76 */     this.implToType = new HashMap();
/* 77 */     for (Iterator iter = this.typeToImpl.keySet().iterator(); iter.hasNext();) {
/* 78 */       Object type = iter.next();
/* 79 */       this.implToType.put(this.typeToImpl.get(type), type);
/*    */     }
/* 81 */     return this;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/DefaultImplementationsMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */