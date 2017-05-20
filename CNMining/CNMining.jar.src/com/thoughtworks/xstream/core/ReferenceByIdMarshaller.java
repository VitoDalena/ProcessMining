/*    */ package com.thoughtworks.xstream.core;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.io.path.Path;
/*    */ import com.thoughtworks.xstream.mapper.Mapper;
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
/*    */ public class ReferenceByIdMarshaller
/*    */   extends AbstractReferenceMarshaller
/*    */ {
/*    */   private final IDGenerator idGenerator;
/*    */   
/*    */   public ReferenceByIdMarshaller(HierarchicalStreamWriter writer, ConverterLookup converterLookup, Mapper mapper, IDGenerator idGenerator)
/*    */   {
/* 31 */     super(writer, converterLookup, mapper);
/* 32 */     this.idGenerator = idGenerator;
/*    */   }
/*    */   
/*    */ 
/*    */   public ReferenceByIdMarshaller(HierarchicalStreamWriter writer, ConverterLookup converterLookup, Mapper mapper)
/*    */   {
/* 38 */     this(writer, converterLookup, mapper, new SequenceGenerator(1));
/*    */   }
/*    */   
/*    */   protected String createReference(Path currentPath, Object existingReferenceKey) {
/* 42 */     return existingReferenceKey.toString();
/*    */   }
/*    */   
/*    */   protected Object createReferenceKey(Path currentPath, Object item) {
/* 46 */     return this.idGenerator.next(item);
/*    */   }
/*    */   
/*    */   protected void fireValidReference(Object referenceKey) {
/* 50 */     String attributeName = getMapper().aliasForSystemAttribute("id");
/* 51 */     if (attributeName != null) {
/* 52 */       this.writer.addAttribute(attributeName, referenceKey.toString());
/*    */     }
/*    */   }
/*    */   
/*    */   public static abstract interface IDGenerator
/*    */   {
/*    */     public abstract String next(Object paramObject);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/ReferenceByIdMarshaller.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */