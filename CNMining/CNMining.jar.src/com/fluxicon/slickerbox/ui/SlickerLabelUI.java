/*    */ package com.fluxicon.slickerbox.ui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.plaf.basic.BasicLabelUI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlickerLabelUI
/*    */   extends BasicLabelUI
/*    */ {
/*    */   public void installUI(JComponent c)
/*    */   {
/* 61 */     super.installUI(c);
/* 62 */     JLabel label = (JLabel)c;
/* 63 */     label.setOpaque(false);
/* 64 */     label.setForeground(new Color(10, 10, 10));
/* 65 */     label.setFont(label.getFont().deriveFont(11.0F));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/ui/SlickerLabelUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */