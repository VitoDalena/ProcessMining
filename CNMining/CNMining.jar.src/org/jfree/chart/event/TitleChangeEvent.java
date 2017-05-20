/*    */ package org.jfree.chart.event;
/*    */ 
/*    */ import org.jfree.chart.title.Title;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TitleChangeEvent
/*    */   extends ChartChangeEvent
/*    */ {
/*    */   private Title title;
/*    */   
/*    */   public TitleChangeEvent(Title title)
/*    */   {
/* 64 */     super(title);
/* 65 */     this.title = title;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Title getTitle()
/*    */   {
/* 74 */     return this.title;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/event/TitleChangeEvent.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */