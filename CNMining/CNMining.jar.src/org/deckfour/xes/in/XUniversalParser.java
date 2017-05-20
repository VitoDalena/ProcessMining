/*    */ package org.deckfour.xes.in;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.Collection;
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
/*    */ 
/*    */ public class XUniversalParser
/*    */ {
/*    */   public boolean canParse(File file)
/*    */   {
/* 60 */     for (XParser parser : XParserRegistry.instance().getAvailable()) {
/* 61 */       if (parser.canParse(file)) {
/* 62 */         return true;
/*    */       }
/*    */     }
/* 65 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Collection<XLog> parse(File file)
/*    */     throws Exception
/*    */   {
/* 73 */     Collection<XLog> result = null;
/* 74 */     for (XParser parser : XParserRegistry.instance().getAvailable()) {
/* 75 */       if (parser.canParse(file)) {
/*    */         try {
/* 77 */           return parser.parse(file);
/*    */         }
/*    */         catch (Exception e) {}
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 84 */     throw new Exception("No suitable parser could be found!");
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/in/XUniversalParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */