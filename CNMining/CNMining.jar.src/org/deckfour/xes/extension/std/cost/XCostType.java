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
/*    */ public class XCostType
/*    */   extends XAbstractNestedAttributeSupport<String>
/*    */ {
/* 51 */   private static transient XCostType singleton = new XCostType();
/*    */   
/*    */   public static XCostType instance() {
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
/* 65 */     return XCostExtension.instance().extractType(attribute);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void assignValue(XAttribute attribute, String value)
/*    */   {
/* 73 */     XCostExtension.instance().assignType(attribute, value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/cost/XCostType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */