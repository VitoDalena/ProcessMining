/*     */ package edu.uci.ics.jung.visualization.renderers;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
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
/*     */ public class DefaultVertexLabelRenderer
/*     */   extends JLabel
/*     */   implements VertexLabelRenderer, Serializable
/*     */ {
/*  36 */   protected static Border noFocusBorder = new EmptyBorder(0, 0, 0, 0);
/*     */   
/*  38 */   protected Color pickedVertexLabelColor = Color.black;
/*     */   
/*     */ 
/*     */ 
/*     */   public DefaultVertexLabelRenderer(Color pickedVertexLabelColor)
/*     */   {
/*  44 */     this.pickedVertexLabelColor = pickedVertexLabelColor;
/*  45 */     setOpaque(true);
/*  46 */     setBorder(noFocusBorder);
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
/*  57 */     super.setForeground(c);
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
/*  68 */     super.setBackground(c);
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
/*  81 */     super.updateUI();
/*  82 */     setForeground(null);
/*  83 */     setBackground(null);
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
/*     */   public <V> Component getVertexLabelRendererComponent(JComponent vv, Object value, Font font, boolean isSelected, V vertex)
/*     */   {
/*  99 */     super.setForeground(vv.getForeground());
/* 100 */     if (isSelected) setForeground(this.pickedVertexLabelColor);
/* 101 */     super.setBackground(vv.getBackground());
/* 102 */     if (font != null) {
/* 103 */       setFont(font);
/*     */     } else {
/* 105 */       setFont(vv.getFont());
/*     */     }
/* 107 */     setIcon(null);
/* 108 */     setBorder(noFocusBorder);
/* 109 */     setValue(value);
/* 110 */     return this;
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
/* 128 */     Color back = getBackground();
/* 129 */     Component p = getParent();
/* 130 */     if (p != null) {
/* 131 */       p = p.getParent();
/*     */     }
/* 133 */     boolean colorMatch = (back != null) && (p != null) && (back.equals(p.getBackground())) && (p.isOpaque());
/*     */     
/*     */ 
/* 136 */     return (!colorMatch) && (super.isOpaque());
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
/* 179 */     if (propertyName == "text") {
/* 180 */       super.firePropertyChange(propertyName, oldValue, newValue);
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
/* 202 */     setText(value == null ? "" : value.toString());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/DefaultVertexLabelRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */