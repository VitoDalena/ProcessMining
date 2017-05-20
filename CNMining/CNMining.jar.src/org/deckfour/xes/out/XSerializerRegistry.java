/*    */ package org.deckfour.xes.out;
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
/*    */ public class XSerializerRegistry
/*    */   extends XRegistry<XSerializer>
/*    */ {
/* 59 */   private static XSerializerRegistry singleton = new XSerializerRegistry();
/*    */   
/*    */ 
/*    */ 
/*    */   public static XSerializerRegistry instance()
/*    */   {
/* 65 */     return singleton;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private XSerializerRegistry()
/*    */   {
/* 73 */     register(new XMxmlSerializer());
/* 74 */     register(new XMxmlGZIPSerializer());
/* 75 */     register(new XesXmlSerializer());
/* 76 */     setCurrentDefault(new XesXmlGZIPSerializer());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected boolean areEqual(XSerializer a, XSerializer b)
/*    */   {
/* 84 */     return a.getClass().equals(b.getClass());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/out/XSerializerRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */