/*    */ package com.thoughtworks.xstream.io.path;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.io.WriterWrapper;
/*    */ import com.thoughtworks.xstream.io.xml.XmlFriendlyWriter;
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
/*    */ public class PathTrackingWriter
/*    */   extends WriterWrapper
/*    */ {
/*    */   private final PathTracker pathTracker;
/*    */   private final boolean isXmlFriendly;
/*    */   
/*    */   public PathTrackingWriter(HierarchicalStreamWriter writer, PathTracker pathTracker)
/*    */   {
/* 32 */     super(writer);
/* 33 */     this.isXmlFriendly = (writer.underlyingWriter() instanceof XmlFriendlyWriter);
/* 34 */     this.pathTracker = pathTracker;
/*    */   }
/*    */   
/*    */   public void startNode(String name) {
/* 38 */     this.pathTracker.pushElement(this.isXmlFriendly ? ((XmlFriendlyWriter)this.wrapped.underlyingWriter()).escapeXmlName(name) : name);
/* 39 */     super.startNode(name);
/*    */   }
/*    */   
/*    */   public void startNode(String name, Class clazz) {
/* 43 */     this.pathTracker.pushElement(this.isXmlFriendly ? ((XmlFriendlyWriter)this.wrapped.underlyingWriter()).escapeXmlName(name) : name);
/* 44 */     super.startNode(name, clazz);
/*    */   }
/*    */   
/*    */   public void endNode() {
/* 48 */     super.endNode();
/* 49 */     this.pathTracker.popElement();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/path/PathTrackingWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */