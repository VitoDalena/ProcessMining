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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OverLIBToolTipTagFragmentGenerator
/*    */   implements ToolTipTagFragmentGenerator
/*    */ {
/*    */   public String generateToolTipFragment(String toolTipText)
/*    */   {
/* 68 */     return " onMouseOver=\"return overlib('" + ImageMapUtilities.javascriptEscape(toolTipText) + "');\" onMouseOut=\"return nd();\"";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/imagemap/OverLIBToolTipTagFragmentGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */