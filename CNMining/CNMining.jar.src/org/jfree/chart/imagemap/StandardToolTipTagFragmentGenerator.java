/*    */ package org.jfree.chart.imagemap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StandardToolTipTagFragmentGenerator
/*    */   implements ToolTipTagFragmentGenerator
/*    */ {
/*    */   public String generateToolTipFragment(String toolTipText)
/*    */   {
/* 65 */     return " title=\"" + ImageMapUtilities.htmlEscape(toolTipText) + "\" alt=\"\"";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/imagemap/StandardToolTipTagFragmentGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */