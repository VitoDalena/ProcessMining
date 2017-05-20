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
/*    */ public class XCostAmount
/*    */   extends XAbstractNestedAttributeSupport<Double>
/*    */ {
/* 51 */   private static transient XCostAmount singleton = new XCostAmount();
/*    */   
/*    */   public static XCostAmount instance() {
/* 54 */     return singleton;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Double extractValue(XAttribute attribute)
/*    */   {
/* 65 */     return XCostExtension.instance().extractAmount(attribute);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void assignValue(XAttribute attribute, Double value)
/*    */   {
/* 73 */     XCostExtension.instance().assignAmount(attribute, value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/cost/XCostAmount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */