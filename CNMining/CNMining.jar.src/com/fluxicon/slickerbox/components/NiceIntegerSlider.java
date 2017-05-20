/*    */ package com.fluxicon.slickerbox.components;
/*    */ 
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JSlider;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NiceIntegerSlider
/*    */   extends NiceSlider
/*    */ {
/*    */   public NiceIntegerSlider(String title, int min, int max, int initial, NiceSlider.Orientation orientation)
/*    */   {
/* 57 */     super(title, min, max, initial, orientation);
/*    */   }
/*    */   
/*    */   public NiceIntegerSlider(String title, int min, int max, int initial)
/*    */   {
/* 62 */     this(title, min, max, initial, NiceSlider.Orientation.HORIZONTAL);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected String formatValue(int value)
/*    */   {
/* 71 */     return Integer.toString(value);
/*    */   }
/*    */   
/*    */   public int getValue() {
/* 75 */     return this.slider.getValue();
/*    */   }
/*    */   
/*    */   public void setValue(int value) {
/* 79 */     this.slider.setValue(value);
/* 80 */     this.label.setText(formatValue(value));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/NiceIntegerSlider.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */