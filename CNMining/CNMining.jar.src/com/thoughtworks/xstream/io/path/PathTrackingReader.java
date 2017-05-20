/*    */ package com.thoughtworks.xstream.io.path;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ErrorWriter;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.ReaderWrapper;
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
/*    */ public class PathTrackingReader
/*    */   extends ReaderWrapper
/*    */ {
/*    */   private final PathTracker pathTracker;
/*    */   
/*    */   public PathTrackingReader(HierarchicalStreamReader reader, PathTracker pathTracker)
/*    */   {
/* 31 */     super(reader);
/* 32 */     this.pathTracker = pathTracker;
/* 33 */     pathTracker.pushElement(getNodeName());
/*    */   }
/*    */   
/*    */   public void moveDown() {
/* 37 */     super.moveDown();
/* 38 */     this.pathTracker.pushElement(getNodeName());
/*    */   }
/*    */   
/*    */   public void moveUp() {
/* 42 */     super.moveUp();
/* 43 */     this.pathTracker.popElement();
/*    */   }
/*    */   
/*    */   public void appendErrors(ErrorWriter errorWriter) {
/* 47 */     errorWriter.add("path", this.pathTracker.getPath().toString());
/* 48 */     super.appendErrors(errorWriter);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/path/PathTrackingReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */