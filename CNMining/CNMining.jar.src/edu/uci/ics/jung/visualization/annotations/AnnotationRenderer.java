/*     */ package edu.uci.ics.jung.visualization.annotations;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Rectangle;
/*     */ import java.io.Serializable;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.EmptyBorder;
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
/*     */ public class AnnotationRenderer
/*     */   extends JLabel
/*     */   implements Serializable
/*     */ {
/*  35 */   protected static Border noFocusBorder = new EmptyBorder(0, 0, 0, 0);
/*     */   
/*     */ 
/*     */ 
/*     */   public AnnotationRenderer()
/*     */   {
/*  41 */     setOpaque(true);
/*  42 */     setBorder(noFocusBorder);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setForeground(Color c)
/*     */   {
/*  53 */     super.setForeground(c);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBackground(Color c)
/*     */   {
/*  64 */     super.setBackground(c);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateUI()
/*     */   {
/*  77 */     super.updateUI();
/*  78 */     setForeground(null);
/*  79 */     setBackground(null);
/*     */   }
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
/*     */   public Component getAnnotationRendererComponent(JComponent vv, Object value)
/*     */   {
/*  94 */     super.setForeground(vv.getForeground());
/*  95 */     super.setBackground(vv.getBackground());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 100 */     setFont(vv.getFont());
/*     */     
/* 102 */     setIcon(null);
/* 103 */     setBorder(noFocusBorder);
/* 104 */     setValue(value);
/* 105 */     return this;
/*     */   }
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
/*     */   public boolean isOpaque()
/*     */   {
/* 123 */     Color back = getBackground();
/* 124 */     Component p = getParent();
/* 125 */     if (p != null) {
/* 126 */       p = p.getParent();
/*     */     }
/* 128 */     boolean colorMatch = (back != null) && (p != null) && (back.equals(p.getBackground())) && (p.isOpaque());
/*     */     
/*     */ 
/* 131 */     return (!colorMatch) && (super.isOpaque());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void validate() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void revalidate() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void repaint(long tm, int x, int y, int width, int height) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void repaint(Rectangle r) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void firePropertyChange(String propertyName, Object oldValue, Object newValue)
/*     */   {
/* 174 */     if (propertyName == "text") {
/* 175 */       super.firePropertyChange(propertyName, oldValue, newValue);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setValue(Object value)
/*     */   {
/* 197 */     setText(value == null ? "" : value.toString());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/annotations/AnnotationRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */