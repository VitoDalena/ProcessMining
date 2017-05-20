/*    */ package org.deckfour.xes.factory;
/*    */ 
/*    */ import org.deckfour.xes.util.XRegistry;
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
/*    */ public class XFactoryRegistry
/*    */   extends XRegistry<XFactory>
/*    */ {
/* 67 */   private static XFactoryRegistry singleton = new XFactoryRegistry();
/*    */   
/*    */ 
/*    */ 
/*    */   public static XFactoryRegistry instance()
/*    */   {
/* 73 */     return singleton;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private XFactoryRegistry()
/*    */   {
/* 81 */     register(new XFactoryNaiveImpl());
/* 82 */     setCurrentDefault(new XFactoryBufferedImpl());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected boolean areEqual(XFactory a, XFactory b)
/*    */   {
/* 90 */     return a.getClass().equals(b.getClass());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/factory/XFactoryRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */