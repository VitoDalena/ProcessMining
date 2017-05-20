/*    */ package org.deckfour.xes.info;
/*    */ 
/*    */ import org.deckfour.xes.classification.XEventClassifier;
/*    */ import org.deckfour.xes.info.impl.XLogInfoImpl;
/*    */ import org.deckfour.xes.model.XLog;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XLogInfoFactory
/*    */ {
/*    */   public static XLogInfo createLogInfo(XLog log)
/*    */   {
/* 59 */     return createLogInfo(log, XLogInfoImpl.STANDARD_CLASSIFIER);
/*    */   }
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
/*    */   public static XLogInfo createLogInfo(XLog log, XEventClassifier classifier)
/*    */   {
/* 73 */     XLogInfo info = log.getInfo(classifier);
/* 74 */     if (info == null)
/*    */     {
/*    */ 
/*    */ 
/* 78 */       info = XLogInfoImpl.create(log, classifier);
/*    */       
/*    */ 
/*    */ 
/* 82 */       log.setInfo(classifier, info);
/*    */     }
/* 84 */     return info;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/info/XLogInfoFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */