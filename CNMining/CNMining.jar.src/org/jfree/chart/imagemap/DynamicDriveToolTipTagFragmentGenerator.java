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
/*    */ public class DynamicDriveToolTipTagFragmentGenerator
/*    */   implements ToolTipTagFragmentGenerator
/*    */ {
/* 54 */   protected String title = "";
/*    */   
/*    */ 
/* 57 */   protected int style = 1;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DynamicDriveToolTipTagFragmentGenerator() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DynamicDriveToolTipTagFragmentGenerator(String title, int style)
/*    */   {
/* 75 */     this.title = title;
/* 76 */     this.style = style;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String generateToolTipFragment(String toolTipText)
/*    */   {
/* 87 */     return " onMouseOver=\"return stm(['" + ImageMapUtilities.javascriptEscape(this.title) + "','" + ImageMapUtilities.javascriptEscape(toolTipText) + "'],Style[" + this.style + "]);\"" + " onMouseOut=\"return htm();\"";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/imagemap/DynamicDriveToolTipTagFragmentGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */