/*     */ package com.fluxicon.slickerbox.util;
/*     */ 
/*     */ import com.fluxicon.slickerbox.ui.SlickerCheckBoxUI;
/*     */ import com.fluxicon.slickerbox.ui.SlickerProgressBarUI;
/*     */ import com.fluxicon.slickerbox.ui.SlickerRadioButtonUI;
/*     */ import com.fluxicon.slickerbox.ui.SlickerScrollBarUI;
/*     */ import com.fluxicon.slickerbox.ui.SlickerSliderUI;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.JSplitPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.plaf.basic.BasicTabbedPaneUI;
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
/*     */ 
/*     */ public class SlickerSwingUtils
/*     */ {
/*  79 */   protected static Color standardBackground = new JPanel().getBackground();
/*     */   
/*     */   public static void injectTransparency(JComponent component) {
/*  82 */     if ((component.isOpaque()) && 
/*  83 */       (component.getBackground().equals(standardBackground)))
/*     */     {
/*  85 */       if (((component instanceof JButton)) || 
/*  86 */         ((component instanceof JComboBox)) || 
/*  87 */         ((component instanceof JSpinner)))
/*     */       {
/*  89 */         if (RuntimeUtils.isRunningMacOsX()) {
/*  90 */           component.setOpaque(false);
/*     */         }
/*  92 */       } else if ((!(component instanceof JTextField)) && 
/*  93 */         (!(component instanceof JTextArea)))
/*     */       {
/*     */ 
/*     */ 
/*  97 */         component.setOpaque(false);
/*     */       }
/*     */     }
/*     */     Component[] arrayOfComponent;
/* 101 */     int j = (arrayOfComponent = component.getComponents()).length; for (int i = 0; i < j; i++) { Component child = arrayOfComponent[i];
/* 102 */       if ((child instanceof JComponent))
/*     */       {
/* 104 */         injectTransparency((JComponent)child);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void injectBackgroundColor(Component component, Color background) {
/* 110 */     if ((component.isOpaque()) && (component.getBackground() != null) && 
/* 111 */       (component.getBackground().equals(standardBackground)))
/*     */     {
/* 113 */       if (((component instanceof JButton)) || 
/* 114 */         ((component instanceof JComboBox)) || 
/* 115 */         ((component instanceof JSpinner)))
/*     */       {
/* 117 */         if (RuntimeUtils.isRunningMacOsX()) {
/* 118 */           component.setBackground(background);
/*     */         }
/* 120 */       } else if ((!(component instanceof JTextField)) && 
/* 121 */         (!(component instanceof JTextArea)))
/*     */       {
/*     */ 
/*     */ 
/* 125 */         component.setBackground(background);
/*     */       }
/*     */     }
/*     */     
/* 129 */     if ((component instanceof JSplitPane)) {
/* 130 */       ((JSplitPane)component).setBorder(BorderFactory.createEmptyBorder());
/* 131 */     } else if ((component instanceof JTabbedPane)) {
/* 132 */       ((JTabbedPane)component).setUI(new BasicTabbedPaneUI());
/* 133 */       component.setBackground(background);
/*     */     }
/*     */     
/* 136 */     if ((component instanceof Container)) {
/* 137 */       Container container = (Container)component;
/* 138 */       Component[] arrayOfComponent; int j = (arrayOfComponent = container.getComponents()).length; for (int i = 0; i < j; i++) { Component child = arrayOfComponent[i];
/* 139 */         if (child != null)
/*     */         {
/*     */ 
/*     */ 
/* 143 */           injectBackgroundColor(child, background); }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void injectSlickerStyle(Component component, Color background) {
/* 149 */     if ((component.isOpaque()) && (component.getBackground() != null) && 
/* 150 */       (component.getBackground().equals(standardBackground)))
/*     */     {
/* 152 */       if (((component instanceof JButton)) || 
/* 153 */         ((component instanceof JComboBox)) || 
/* 154 */         ((component instanceof JSpinner)))
/*     */       {
/* 156 */         if (RuntimeUtils.isRunningMacOsX()) {
/* 157 */           component.setBackground(background);
/*     */         }
/* 159 */       } else if ((!(component instanceof JTextField)) && 
/* 160 */         (!(component instanceof JTextArea)))
/*     */       {
/*     */ 
/*     */ 
/* 164 */         component.setBackground(background);
/*     */       }
/*     */     }
/*     */     
/* 168 */     if ((component instanceof JSplitPane)) {
/* 169 */       ((JSplitPane)component).setBorder(BorderFactory.createEmptyBorder());
/* 170 */     } else if ((component instanceof JTabbedPane)) {
/* 171 */       ((JTabbedPane)component).setUI(new BasicTabbedPaneUI());
/* 172 */       component.setBackground(background);
/* 173 */     } else if ((component instanceof JCheckBox)) {
/* 174 */       ((JCheckBox)component).setUI(new SlickerCheckBoxUI());
/* 175 */     } else if ((component instanceof JRadioButton)) {
/* 176 */       ((JRadioButton)component).setUI(new SlickerRadioButtonUI());
/* 177 */     } else if ((component instanceof JScrollBar)) {
/* 178 */       JScrollBar scrollBar = (JScrollBar)component;
/* 179 */       scrollBar.setUI(new SlickerScrollBarUI(scrollBar, background, new Color(30, 30, 30), new Color(50, 50, 50), 3.0F, 12.0F));
/* 180 */     } else if ((component instanceof JScrollPane)) {
/* 181 */       ((JScrollPane)component).setBackground(background);
/* 182 */     } else if ((component instanceof JSlider)) {
/* 183 */       ((JSlider)component).setUI(new SlickerSliderUI((JSlider)component));
/* 184 */     } else if ((component instanceof JProgressBar)) {
/* 185 */       ((JProgressBar)component).setUI(new SlickerProgressBarUI());
/*     */     }
/*     */     
/* 188 */     if ((component instanceof Container)) {
/* 189 */       Container container = (Container)component;
/* 190 */       Component[] arrayOfComponent; int j = (arrayOfComponent = container.getComponents()).length; for (int i = 0; i < j; i++) { Component child = arrayOfComponent[i];
/* 191 */         if (child != null)
/*     */         {
/*     */ 
/*     */ 
/* 195 */           injectSlickerStyle(child, background);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/util/SlickerSwingUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */