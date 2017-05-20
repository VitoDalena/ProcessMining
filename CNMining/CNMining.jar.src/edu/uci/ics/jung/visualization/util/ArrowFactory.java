/*    */ package edu.uci.ics.jung.visualization.util;
/*    */ 
/*    */ import java.awt.geom.GeneralPath;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArrowFactory
/*    */ {
/*    */   public static GeneralPath getWedgeArrow(float base, float height)
/*    */   {
/* 31 */     GeneralPath arrow = new GeneralPath();
/* 32 */     arrow.moveTo(0.0F, 0.0F);
/* 33 */     arrow.lineTo(-height, base / 2.0F);
/* 34 */     arrow.lineTo(-height, -base / 2.0F);
/* 35 */     arrow.lineTo(0.0F, 0.0F);
/* 36 */     return arrow;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static GeneralPath getNotchedArrow(float base, float height, float notch_height)
/*    */   {
/* 48 */     GeneralPath arrow = new GeneralPath();
/* 49 */     arrow.moveTo(0.0F, 0.0F);
/* 50 */     arrow.lineTo(-height, base / 2.0F);
/* 51 */     arrow.lineTo(-(height - notch_height), 0.0F);
/* 52 */     arrow.lineTo(-height, -base / 2.0F);
/* 53 */     arrow.lineTo(0.0F, 0.0F);
/* 54 */     return arrow;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/util/ArrowFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */