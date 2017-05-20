/*    */ package org.deckfour.xes.model;
/*    */ 
/*    */ import org.deckfour.xes.classification.XEventClassifier;
/*    */ import org.deckfour.xes.extension.XExtension;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class XVisitor
/*    */ {
/*    */   public boolean precondition()
/*    */   {
/* 54 */     return true;
/*    */   }
/*    */   
/*    */   public void init(XLog log) {}
/*    */   
/*    */   public void visitLogPre(XLog log) {}
/*    */   
/*    */   public void visitLogPost(XLog log) {}
/*    */   
/*    */   public void visitExtensionPre(XExtension ext, XLog log) {}
/*    */   
/*    */   public void visitExtensionPost(XExtension ext, XLog log) {}
/*    */   
/*    */   public void visitClassifierPre(XEventClassifier classifier, XLog log) {}
/*    */   
/*    */   public void visitClassifierPost(XEventClassifier classifier, XLog log) {}
/*    */   
/*    */   public void visitTracePre(XTrace trace, XLog log) {}
/*    */   
/*    */   public void visitTracePost(XTrace trace, XLog log) {}
/*    */   
/*    */   public void visitEventPre(XEvent event, XTrace trace) {}
/*    */   
/*    */   public void visitEventPost(XEvent event, XTrace trace) {}
/*    */   
/*    */   public void visitAttributePre(XAttribute attr, XAttributable parent) {}
/*    */   
/*    */   public void visitAttributePost(XAttribute attr, XAttributable parent) {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/XVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */