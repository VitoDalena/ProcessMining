/*    */ package com.thoughtworks.xstream.core;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ConverterLookup;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.path.Path;
/*    */ import com.thoughtworks.xstream.io.path.PathTracker;
/*    */ import com.thoughtworks.xstream.io.path.PathTrackingReader;
/*    */ import com.thoughtworks.xstream.io.xml.XmlFriendlyReader;
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
/*    */ public class ReferenceByXPathUnmarshaller
/*    */   extends AbstractReferenceUnmarshaller
/*    */ {
/* 24 */   private PathTracker pathTracker = new PathTracker();
/*    */   protected boolean isXmlFriendly;
/*    */   
/*    */   public ReferenceByXPathUnmarshaller(Object root, HierarchicalStreamReader reader, ConverterLookup converterLookup, Mapper mapper)
/*    */   {
/* 29 */     super(root, reader, converterLookup, mapper);
/* 30 */     this.reader = new PathTrackingReader(reader, this.pathTracker);
/* 31 */     this.isXmlFriendly = (reader.underlyingReader() instanceof XmlFriendlyReader);
/*    */   }
/*    */   
/*    */   protected Object getReferenceKey(String reference) {
/* 35 */     Path path = new Path(this.isXmlFriendly ? ((XmlFriendlyReader)this.reader.underlyingReader()).unescapeXmlName(reference) : reference);
/*    */     
/* 37 */     return reference.charAt(0) != '/' ? this.pathTracker.getPath().apply(path) : path;
/*    */   }
/*    */   
/*    */   protected Object getCurrentReferenceKey() {
/* 41 */     return this.pathTracker.getPath();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/ReferenceByXPathUnmarshaller.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */