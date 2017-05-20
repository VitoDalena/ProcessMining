/*     */ package edu.uci.ics.jung.visualization.annotations;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Shape;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.awt.geom.RectangularShape;
/*     */ import java.awt.geom.RoundRectangle2D.Double;
/*     */ import javax.swing.DefaultListCellRenderer;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JToggleButton;
/*     */ import javax.swing.JToolBar;
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
/*     */ public class AnnotationControls<V, E>
/*     */ {
/*     */   protected AnnotatingGraphMousePlugin<V, E> annotatingPlugin;
/*     */   
/*     */   public AnnotationControls(AnnotatingGraphMousePlugin<V, E> annotatingPlugin)
/*     */   {
/*  43 */     this.annotatingPlugin = annotatingPlugin;
/*     */   }
/*     */   
/*     */   public JComboBox getShapeBox()
/*     */   {
/*  48 */     JComboBox shapeBox = new JComboBox(new Shape[] { new Rectangle2D.Double(), new RoundRectangle2D.Double(0.0D, 0.0D, 0.0D, 0.0D, 50.0D, 50.0D), new Ellipse2D.Double() });
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  54 */     shapeBox.setRenderer(new DefaultListCellRenderer()
/*     */     {
/*     */       public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus)
/*     */       {
/*  58 */         String valueString = value.toString();
/*  59 */         valueString = valueString.substring(0, valueString.indexOf("2D"));
/*  60 */         valueString = valueString.substring(valueString.lastIndexOf('.') + 1);
/*  61 */         return super.getListCellRendererComponent(list, valueString, index, isSelected, hasFocus);
/*     */       }
/*     */       
/*  64 */     });
/*  65 */     shapeBox.addItemListener(new ItemListener()
/*     */     {
/*     */       public void itemStateChanged(ItemEvent e) {
/*  68 */         if (e.getStateChange() == 1) {
/*  69 */           AnnotationControls.this.annotatingPlugin.setRectangularShape((RectangularShape)e.getItem());
/*     */         }
/*     */       }
/*  72 */     });
/*  73 */     return shapeBox;
/*     */   }
/*     */   
/*     */   public JButton getColorChooserButton() {
/*  77 */     final JButton colorChooser = new JButton("Color");
/*  78 */     colorChooser.setForeground(this.annotatingPlugin.getAnnotationColor());
/*  79 */     colorChooser.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  82 */         Color color = JColorChooser.showDialog(colorChooser, "Annotation Color", colorChooser.getForeground());
/*     */         
/*  84 */         AnnotationControls.this.annotatingPlugin.setAnnotationColor(color);
/*  85 */         colorChooser.setForeground(color);
/*  86 */       } });
/*  87 */     return colorChooser;
/*     */   }
/*     */   
/*     */   public JComboBox getLayerBox() {
/*  91 */     JComboBox layerBox = new JComboBox(new Annotation.Layer[] { Annotation.Layer.LOWER, Annotation.Layer.UPPER });
/*     */     
/*     */ 
/*     */ 
/*  95 */     layerBox.addItemListener(new ItemListener()
/*     */     {
/*     */       public void itemStateChanged(ItemEvent e) {
/*  98 */         if (e.getStateChange() == 1) {
/*  99 */           AnnotationControls.this.annotatingPlugin.setLayer((Annotation.Layer)e.getItem());
/*     */         }
/*     */         
/*     */       }
/* 103 */     });
/* 104 */     return layerBox;
/*     */   }
/*     */   
/*     */   public JToggleButton getFillButton() {
/* 108 */     JToggleButton fillButton = new JToggleButton("Fill");
/* 109 */     fillButton.addItemListener(new ItemListener()
/*     */     {
/*     */       public void itemStateChanged(ItemEvent e) {
/* 112 */         AnnotationControls.this.annotatingPlugin.setFill(e.getStateChange() == 1);
/*     */       }
/* 114 */     });
/* 115 */     return fillButton;
/*     */   }
/*     */   
/*     */   public JToolBar getAnnotationsToolBar() {
/* 119 */     JToolBar toolBar = new JToolBar();
/* 120 */     toolBar.add(getShapeBox());
/* 121 */     toolBar.add(getColorChooserButton());
/* 122 */     toolBar.add(getFillButton());
/* 123 */     toolBar.add(getLayerBox());
/* 124 */     return toolBar;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/annotations/AnnotationControls.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */