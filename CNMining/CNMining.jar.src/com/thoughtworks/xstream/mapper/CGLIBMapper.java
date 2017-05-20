/*    */ package com.thoughtworks.xstream.mapper;
/*    */ 
/*    */ import net.sf.cglib.proxy.Enhancer;
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
/*    */ 
/*    */ 
/*    */ public class CGLIBMapper
/*    */   extends MapperWrapper
/*    */ {
/* 28 */   private static String DEFAULT_NAMING_MARKER = "$$EnhancerByCGLIB$$";
/*    */   
/*    */   private final String alias;
/*    */   
/*    */ 
/*    */   public CGLIBMapper(Mapper wrapped)
/*    */   {
/* 35 */     this(wrapped, "CGLIB-enhanced-proxy");
/*    */   }
/*    */   
/*    */   public CGLIBMapper(Mapper wrapped, String alias) {
/* 39 */     super(wrapped);
/* 40 */     this.alias = alias;
/*    */   }
/*    */   
/*    */   public String serializedClass(Class type) {
/* 44 */     String serializedName = super.serializedClass(type);
/* 45 */     if (type == null) {
/* 46 */       return serializedName;
/*    */     }
/* 48 */     String typeName = type.getName();
/* 49 */     return (typeName.equals(serializedName)) && (typeName.indexOf(DEFAULT_NAMING_MARKER) > 0) && (Enhancer.isEnhanced(type)) ? this.alias : serializedName;
/*    */   }
/*    */   
/*    */ 
/*    */   public Class realClass(String elementName)
/*    */   {
/* 55 */     return elementName.equals(this.alias) ? Marker.class : super.realClass(elementName);
/*    */   }
/*    */   
/*    */   public static abstract interface Marker {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/CGLIBMapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */