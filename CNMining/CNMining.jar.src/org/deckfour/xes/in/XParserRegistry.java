/*    */ package org.deckfour.xes.in;
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
/*    */ public class XParserRegistry
/*    */   extends XRegistry<XParser>
/*    */ {
/* 59 */   private static XParserRegistry singleton = new XParserRegistry();
/*    */   
/*    */ 
/*    */ 
/*    */   public static XParserRegistry instance()
/*    */   {
/* 65 */     return singleton;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private XParserRegistry()
/*    */   {
/* 73 */     register(new XMxmlParser());
/* 74 */     register(new XMxmlGZIPParser());
/* 75 */     register(new XesXmlParser());
/* 76 */     setCurrentDefault(new XesXmlGZIPParser());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected boolean areEqual(XParser a, XParser b)
/*    */   {
/* 84 */     return a.getClass().equals(b.getClass());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/in/XParserRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */