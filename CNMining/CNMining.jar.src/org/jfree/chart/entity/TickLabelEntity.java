/*    */ package org.jfree.chart.entity;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TickLabelEntity
/*    */   extends ChartEntity
/*    */   implements Cloneable, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 681583956588092095L;
/*    */   
/*    */   public TickLabelEntity(Shape area, String toolTipText, String urlText)
/*    */   {
/* 64 */     super(area, toolTipText, urlText);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/TickLabelEntity.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */