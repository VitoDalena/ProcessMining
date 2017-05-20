/*    */ package org.deckfour.xes.xstream;
/*    */ 
/*    */ import com.thoughtworks.xstream.XStream;
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
/*    */ public class XesXStreamPersistency
/*    */ {
/* 58 */   static final XConverter logConverter = new XLogConverter();
/* 59 */   static final XConverter traceConverter = new XTraceConverter();
/* 60 */   static final XConverter eventConverter = new XEventConverter();
/* 61 */   static final XConverter attributeMapConverter = new XAttributeMapConverter();
/* 62 */   static final XConverter attributeConverter = new XAttributeConverter();
/* 63 */   static final XConverter extensionConverter = new XExtensionConverter();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/* 68 */   private static final XConverter[] converters = { logConverter, traceConverter, eventConverter, attributeMapConverter, attributeConverter, extensionConverter };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void register(XStream stream)
/*    */   {
/* 80 */     for (XConverter converter : converters) {
/* 81 */       stream.registerConverter(converter);
/* 82 */       converter.registerAliases(stream);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/xstream/XesXStreamPersistency.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */