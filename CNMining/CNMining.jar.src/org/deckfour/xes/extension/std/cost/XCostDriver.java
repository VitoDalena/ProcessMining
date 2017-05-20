/*    */ package org.deckfour.xes.extension.std.cost;
/*    */ 
/*    */ import org.deckfour.xes.extension.std.XAbstractNestedAttributeSupport;
/*    */ import org.deckfour.xes.extension.std.XCostExtension;
/*    */ import org.deckfour.xes.model.XAttribute;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XCostDriver
/*    */   extends XAbstractNestedAttributeSupport<String>
/*    */ {
/* 51 */   private static transient XCostDriver singleton = new XCostDriver();
/*    */   
/*    */   public static XCostDriver instance() {
/* 54 */     return singleton;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String extractValue(XAttribute attribute)
/*    */   {
/* 65 */     return XCostExtension.instance().extractDriver(attribute);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void assignValue(XAttribute attribute, String value)
/*    */   {
/* 73 */     XCostExtension.instance().assignDriver(attribute, value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/cost/XCostDriver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */