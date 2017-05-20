/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import com.fluxicon.slickerbox.ui.SlickerSliderUI;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
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
/*     */ public abstract class NiceSlider
/*     */   extends JPanel
/*     */ {
/*     */   protected JSlider slider;
/*     */   protected JLabel title;
/*     */   protected JLabel label;
/*     */   
/*     */   public static enum Orientation
/*     */   {
/*  63 */     HORIZONTAL,  VERTICAL;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected NiceSlider(String title, int min, int max, int initial, Orientation orientation)
/*     */   {
/*  71 */     if (orientation.equals(Orientation.HORIZONTAL)) {
/*  72 */       setMinimumSize(new Dimension(200, 25));
/*  73 */       setMaximumSize(new Dimension(4000, 25));
/*  74 */       setPreferredSize(new Dimension(500, 25));
/*  75 */       this.slider = new JSlider(0, min, max, initial);
/*     */     } else {
/*  77 */       setMinimumSize(new Dimension(50, 100));
/*  78 */       setMaximumSize(new Dimension(200, 4000));
/*  79 */       setPreferredSize(new Dimension(100, 500));
/*  80 */       this.slider = new JSlider(1, min, max, initial);
/*     */     }
/*  82 */     this.slider.setUI(new SlickerSliderUI(this.slider));
/*  83 */     this.slider.setOpaque(false);
/*  84 */     this.slider.addChangeListener(new ChangeListener() {
/*     */       public void stateChanged(ChangeEvent evt) {
/*  86 */         NiceSlider.this.label.setText(NiceSlider.this.formatValue(NiceSlider.this.slider.getValue()));
/*     */       }
/*  88 */     });
/*  89 */     this.slider.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
/*  90 */     this.label = new JLabel(formatValue(initial));
/*  91 */     this.label.setMinimumSize(new Dimension(50, 20));
/*  92 */     this.label.setFont(this.label.getFont().deriveFont(11.0F));
/*  93 */     this.label.setHorizontalAlignment(0);
/*  94 */     this.label.setHorizontalTextPosition(0);
/*  95 */     this.label.setAlignmentX(0.5F);
/*  96 */     this.label.setVerticalAlignment(0);
/*  97 */     this.label.setAlignmentY(0.5F);
/*  98 */     this.label.setVerticalTextPosition(0);
/*  99 */     this.label.setOpaque(false);
/* 100 */     this.title = new JLabel(title + ":");
/* 101 */     this.title.setFont(this.title.getFont().deriveFont(11.0F));
/* 102 */     this.title.setOpaque(false);
/* 103 */     this.title.setHorizontalAlignment(0);
/* 104 */     this.title.setHorizontalTextPosition(0);
/* 105 */     this.title.setAlignmentX(0.5F);
/* 106 */     this.title.setVerticalAlignment(0);
/* 107 */     this.title.setAlignmentY(0.5F);
/* 108 */     this.title.setVerticalTextPosition(0);
/* 109 */     setBorder(BorderFactory.createEmptyBorder());
/* 110 */     setOpaque(false);
/* 111 */     setLayout(new BorderLayout());
/* 112 */     if (orientation.equals(Orientation.HORIZONTAL)) {
/* 113 */       add(this.title, "West");
/* 114 */       add(this.label, "East");
/*     */     } else {
/* 116 */       add(this.title, "North");
/* 117 */       add(this.label, "South");
/*     */     }
/* 119 */     add(this.slider, "Center");
/*     */   }
/*     */   
/*     */   public void addChangeListener(ChangeListener listener) {
/* 123 */     this.slider.addChangeListener(listener);
/*     */   }
/*     */   
/*     */   public JSlider getSlider() {
/* 127 */     return this.slider;
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 131 */     this.slider.setEnabled(enabled);
/*     */   }
/*     */   
/*     */   public boolean getEnabled() {
/* 135 */     return this.slider.isEnabled();
/*     */   }
/*     */   
/*     */   protected abstract String formatValue(int paramInt);
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/NiceSlider.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */