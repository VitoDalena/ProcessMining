/*     */ package com.fluxicon.slickerbox.factory;
/*     */ 
/*     */ import com.fluxicon.slickerbox.ui.SlickerCheckBoxUI;
/*     */ import com.fluxicon.slickerbox.ui.SlickerComboBoxUI;
/*     */ import com.fluxicon.slickerbox.ui.SlickerDarkEstimatingProgressBarUI;
/*     */ import com.fluxicon.slickerbox.ui.SlickerDarkProgressBarUI;
/*     */ import com.fluxicon.slickerbox.ui.SlickerLabelUI;
/*     */ import com.fluxicon.slickerbox.ui.SlickerRadioButtonUI;
/*     */ import com.fluxicon.slickerbox.ui.SlickerScrollBarUI;
/*     */ import com.fluxicon.slickerbox.ui.SlickerSliderUI;
/*     */ import java.awt.Color;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSlider;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlickerDecorator
/*     */ {
/*  70 */   private static final SlickerDecorator instance = new SlickerDecorator();
/*     */   
/*     */   public static final SlickerDecorator instance() {
/*  73 */     return instance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void decorate(JCheckBox checkBox)
/*     */   {
/*  81 */     checkBox.setUI(new SlickerCheckBoxUI());
/*     */   }
/*     */   
/*     */   public void decorate(JComboBox comboBox) {
/*  85 */     comboBox.setUI(new SlickerComboBoxUI());
/*     */   }
/*     */   
/*     */   public void decorate(JProgressBar progressBar) {
/*  89 */     if (progressBar.getOrientation() == 0) {
/*  90 */       progressBar.setUI(new SlickerDarkEstimatingProgressBarUI());
/*     */     } else {
/*  92 */       progressBar.setUI(new SlickerDarkProgressBarUI());
/*     */     }
/*     */   }
/*     */   
/*     */   public void decorate(JLabel label) {
/*  97 */     label.setUI(new SlickerLabelUI());
/*     */   }
/*     */   
/*     */   public void decorate(JRadioButton radioButton) {
/* 101 */     radioButton.setUI(new SlickerRadioButtonUI());
/*     */   }
/*     */   
/*     */   public void decorate(JScrollBar scrollBar, Color background, Color passive, Color active) {
/* 105 */     scrollBar.setUI(new SlickerScrollBarUI(scrollBar, background, passive, active, 5.0F, 13.0F));
/*     */   }
/*     */   
/*     */   public void decorate(JScrollBar scrollBar) {
/* 109 */     scrollBar.setUI(new SlickerScrollBarUI(scrollBar));
/*     */   }
/*     */   
/*     */   public void decorate(JScrollPane scrollPane, Color background, Color passive, Color active) {
/* 113 */     scrollPane.setBackground(background);
/* 114 */     decorate(scrollPane.getHorizontalScrollBar(), background, passive, active);
/* 115 */     decorate(scrollPane.getVerticalScrollBar(), background, passive, active);
/*     */   }
/*     */   
/*     */   public void decorate(JSlider slider) {
/* 119 */     slider.setUI(new SlickerSliderUI(slider));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/factory/SlickerDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */