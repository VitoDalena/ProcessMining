/*    */ package edu.uci.ics.jung.io.graphml.parser;
/*    */ 
/*    */ import javax.xml.stream.EventFilter;
/*    */ import javax.xml.stream.events.XMLEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GraphMLEventFilter
/*    */   implements EventFilter
/*    */ {
/*    */   public boolean accept(XMLEvent event)
/*    */   {
/* 25 */     switch (event.getEventType()) {
/*    */     case 1: 
/*    */     case 2: 
/*    */     case 4: 
/*    */     case 7: 
/*    */     case 8: 
/*    */     case 10: 
/*    */     case 13: 
/* 33 */       return true;
/*    */     }
/*    */     
/* 36 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/GraphMLEventFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */