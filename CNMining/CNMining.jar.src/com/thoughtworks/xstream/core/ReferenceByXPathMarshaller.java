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
/*    */ public class ReferenceByXPathMarshaller
/*    */   extends AbstractReferenceMarshaller
/*    */ {
/*    */   private final int mode;
/*    */   
/*    */   public ReferenceByXPathMarshaller(HierarchicalStreamWriter writer, ConverterLookup converterLookup, Mapper mapper, int mode)
/*    */   {
/* 24 */     super(writer, converterLookup, mapper);
/* 25 */     this.mode = mode;
/*    */   }
/*    */   
/*    */   protected String createReference(Path currentPath, Object existingReferenceKey) {
/* 29 */     Path existingPath = (Path)existingReferenceKey;
/* 30 */     Path referencePath = (this.mode & ReferenceByXPathMarshallingStrategy.ABSOLUTE) > 0 ? existingPath : currentPath.relativeTo(existingPath);
/* 31 */     return (this.mode & ReferenceByXPathMarshallingStrategy.SINGLE_NODE) > 0 ? referencePath.explicit() : referencePath.toString();
/*    */   }
/*    */   
/*    */   protected Object createReferenceKey(Path currentPath, Object item) {
/* 35 */     return currentPath;
/*    */   }
/*    */   
/*    */   protected void fireValidReference(Object referenceKey) {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/ReferenceByXPathMarshaller.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */