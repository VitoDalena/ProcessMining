/*     */ package com.fluxicon.slickerbox.factory;
/*     */ 
/*     */ import com.fluxicon.slickerbox.colors.SlickerColors;
/*     */ import com.fluxicon.slickerbox.components.GradientPanel;
/*     */ import com.fluxicon.slickerbox.components.NiceDoubleSlider;
/*     */ import com.fluxicon.slickerbox.components.NiceIntegerSlider;
/*     */ import com.fluxicon.slickerbox.components.NiceSlider.Orientation;
/*     */ import com.fluxicon.slickerbox.components.RoundedPanel;
/*     */ import com.fluxicon.slickerbox.components.SlickerButton;
/*     */ import com.fluxicon.slickerbox.components.SlickerTabbedPane;
/*     */ import com.fluxicon.slickerbox.components.StackedCardsTabbedPane;
/*     */ import java.awt.Color;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.JRadioButton;
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
/*     */ public class SlickerFactory
/*     */ {
/*  71 */   private static final SlickerFactory instance = new SlickerFactory();
/*     */   
/*     */   public static SlickerFactory instance() {
/*  74 */     return instance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public JLabel createLabel(String text)
/*     */   {
/*  82 */     JLabel label = new JLabel(text);
/*  83 */     SlickerDecorator.instance().decorate(label);
/*  84 */     return label;
/*     */   }
/*     */   
/*     */   public JCheckBox createCheckBox(String label, boolean value) {
/*  88 */     JCheckBox box = new JCheckBox(label, value);
/*  89 */     SlickerDecorator.instance().decorate(box);
/*  90 */     return box;
/*     */   }
/*     */   
/*     */   public JRadioButton createRadioButton(String label) {
/*  94 */     JRadioButton button = new JRadioButton(label);
/*  95 */     SlickerDecorator.instance().decorate(button);
/*  96 */     return button;
/*     */   }
/*     */   
/*     */   public JButton createButton(String label) {
/* 100 */     SlickerButton button = new SlickerButton(label);
/* 101 */     return button;
/*     */   }
/*     */   
/*     */   public JComboBox createComboBox(Object[] values) {
/* 105 */     JComboBox box = new JComboBox(values);
/* 106 */     SlickerDecorator.instance().decorate(box);
/* 107 */     return box;
/*     */   }
/*     */   
/*     */   public JProgressBar createProgressBar(int orientation) {
/* 111 */     JProgressBar bar = new JProgressBar(orientation);
/* 112 */     SlickerDecorator.instance().decorate(bar);
/* 113 */     return bar;
/*     */   }
/*     */   
/*     */   public JSlider createSlider(int orientation) {
/* 117 */     JSlider slider = new JSlider(orientation);
/* 118 */     SlickerDecorator.instance().decorate(slider);
/* 119 */     return slider;
/*     */   }
/*     */   
/*     */   public SlickerTabbedPane createTabbedPane(String title, Color background, Color foreground, Color titlecolor) {
/* 123 */     return new SlickerTabbedPane(title, background, foreground, titlecolor);
/*     */   }
/*     */   
/*     */   public SlickerTabbedPane createTabbedPane(String title) {
/* 127 */     return createTabbedPane(title, SlickerColors.COLOR_TRANSPARENT, SlickerColors.COLOR_FG, SlickerColors.COLOR_BG_3);
/*     */   }
/*     */   
/*     */   public NiceIntegerSlider createNiceIntegerSlider(String title, int min, int max, int initial, NiceSlider.Orientation orientation) {
/* 131 */     return new NiceIntegerSlider(title, min, max, initial, orientation);
/*     */   }
/*     */   
/*     */   public NiceDoubleSlider createNiceDoubleSlider(String title, double min, double max, double initial, NiceSlider.Orientation orientation) {
/* 135 */     return new NiceDoubleSlider(title, min, max, initial, orientation);
/*     */   }
/*     */   
/*     */   public StackedCardsTabbedPane createStackedCardsPane() {
/* 139 */     return new StackedCardsTabbedPane();
/*     */   }
/*     */   
/*     */   public JPanel createRoundedPanel(int radius, Color background) {
/* 143 */     RoundedPanel panel = new RoundedPanel(radius);
/* 144 */     panel.setBackground(background);
/* 145 */     return panel;
/*     */   }
/*     */   
/*     */   public JPanel createRoundedPanel() {
/* 149 */     return createRoundedPanel(5, SlickerColors.COLOR_BG_3);
/*     */   }
/*     */   
/*     */   public JPanel createGradientPanel(Color top, Color bottom) {
/* 153 */     return new GradientPanel(top, bottom);
/*     */   }
/*     */   
/*     */   public JPanel createGradientPanel() {
/* 157 */     return createGradientPanel(SlickerColors.COLOR_BG_2, SlickerColors.COLOR_BG_1);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/factory/SlickerFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */