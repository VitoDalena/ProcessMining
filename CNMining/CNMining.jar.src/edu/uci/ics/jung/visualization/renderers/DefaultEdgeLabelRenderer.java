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
/*     */ public class DefaultEdgeLabelRenderer
/*     */   extends JLabel
/*     */   implements EdgeLabelRenderer, Serializable
/*     */ {
/*  36 */   protected static Border noFocusBorder = new EmptyBorder(0, 0, 0, 0);
/*     */   
/*  38 */   protected Color pickedEdgeLabelColor = Color.black;
/*     */   protected boolean rotateEdgeLabels;
/*     */   
/*     */   public DefaultEdgeLabelRenderer(Color pickedEdgeLabelColor) {
/*  42 */     this(pickedEdgeLabelColor, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultEdgeLabelRenderer(Color pickedEdgeLabelColor, boolean rotateEdgeLabels)
/*     */   {
/*  50 */     this.pickedEdgeLabelColor = pickedEdgeLabelColor;
/*  51 */     this.rotateEdgeLabels = rotateEdgeLabels;
/*  52 */     setOpaque(true);
/*  53 */     setBorder(noFocusBorder);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isRotateEdgeLabels()
/*     */   {
/*  60 */     return this.rotateEdgeLabels;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotateEdgeLabels(boolean rotateEdgeLabels)
/*     */   {
/*  66 */     this.rotateEdgeLabels = rotateEdgeLabels;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setForeground(Color c)
/*     */   {
/*  76 */     super.setForeground(c);
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
/*  87 */     super.setBackground(c);
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
/* 100 */     super.updateUI();
/* 101 */     setForeground(null);
/* 102 */     setBackground(null);
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
/*     */   public <E> Component getEdgeLabelRendererComponent(JComponent vv, Object value, Font font, boolean isSelected, E edge)
/*     */   {
/* 118 */     super.setForeground(vv.getForeground());
/* 119 */     if (isSelected) setForeground(this.pickedEdgeLabelColor);
/* 120 */     super.setBackground(vv.getBackground());
/*     */     
/* 122 */     if (font != null) {
/* 123 */       setFont(font);
/*     */     } else {
/* 125 */       setFont(vv.getFont());
/*     */     }
/* 127 */     setIcon(null);
/* 128 */     setBorder(noFocusBorder);
/* 129 */     setValue(value);
/* 130 */     return this;
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
/* 148 */     Color back = getBackground();
/* 149 */     Component p = getParent();
/* 150 */     if (p != null) {
/* 151 */       p = p.getParent();
/*     */     }
/* 153 */     boolean colorMatch = (back != null) && (p != null) && (back.equals(p.getBackground())) && (p.isOpaque());
/*     */     
/*     */ 
/* 156 */     return (!colorMatch) && (super.isOpaque());
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
/* 199 */     if (propertyName == "text") {
/* 200 */       super.firePropertyChange(propertyName, oldValue, newValue);
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
/* 222 */     setText(value == null ? "" : value.toString());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/DefaultEdgeLabelRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */