/*    */ package com.fluxicon.slickerbox.components;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.NumberFormat;
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
/*    */ public class NiceDoubleSlider
/*    */   extends NiceSlider
/*    */ {
/*    */   protected double min;
/*    */   protected double max;
/* 57 */   protected static NumberFormat format = new DecimalFormat("#.###");
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public NiceDoubleSlider(String title, double min, double max, double initial, NiceSlider.Orientation orientation)
/*    */   {
/* 66 */     super(title, 0, 10000, (int)(10000.0D * (initial - min) / (max - min)), orientation);
/* 67 */     this.min = min;
/* 68 */     this.max = max;
/* 69 */     this.label.setText(formatValue(this.slider.getValue()));
/*    */   }
/*    */   
/*    */   public NiceDoubleSlider(String title, double min, double max, double initial) {
/* 73 */     this(title, min, max, initial, NiceSlider.Orientation.HORIZONTAL);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected String formatValue(int value)
/*    */   {
/* 81 */     return format.format(this.min + value / 10000.0D * (this.max - this.min));
/*    */   }
/*    */   
/*    */   public double getValue() {
/* 85 */     return this.min + this.slider.getValue() / 10000.0D * (this.max - this.min);
/*    */   }
/*    */   
/*    */   public void setValue(double value) {
/* 89 */     double relative = (value - this.min) / (this.max - this.min);
/* 90 */     int intValue = (int)(relative * 10000.0D);
/* 91 */     this.slider.setValue(intValue);
/* 92 */     this.label.setText(format.format(value));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/NiceDoubleSlider.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */