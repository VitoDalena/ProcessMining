/*     */ package org.jfree.chart.editor;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import org.jfree.chart.plot.ColorPalette;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class PaletteSample
/*     */   extends JComponent
/*     */   implements ListCellRenderer
/*     */ {
/*     */   private ColorPalette palette;
/*     */   private Dimension preferredSize;
/*     */   
/*     */   public PaletteSample(ColorPalette palette)
/*     */   {
/*  84 */     this.palette = palette;
/*  85 */     this.preferredSize = new Dimension(80, 18);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/*     */   {
/* 106 */     if ((value instanceof PaletteSample)) {
/* 107 */       PaletteSample in = (PaletteSample)value;
/* 108 */       setPalette(in.getPalette());
/*     */     }
/* 110 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ColorPalette getPalette()
/*     */   {
/* 119 */     return this.palette;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize()
/*     */   {
/* 128 */     return this.preferredSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paintComponent(Graphics g)
/*     */   {
/* 138 */     Graphics2D g2 = (Graphics2D)g;
/* 139 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*     */     
/*     */ 
/* 142 */     Dimension size = getSize();
/* 143 */     Insets insets = getInsets();
/* 144 */     double ww = size.getWidth() - insets.left - insets.right;
/* 145 */     double hh = size.getHeight() - insets.top - insets.bottom;
/*     */     
/* 147 */     g2.setStroke(new BasicStroke(1.0F));
/*     */     
/* 149 */     double y1 = insets.top;
/* 150 */     double y2 = y1 + hh;
/* 151 */     double xx = insets.left;
/* 152 */     Line2D line = new Line2D.Double();
/* 153 */     int count = 0;
/* 154 */     while (xx <= insets.left + ww) {
/* 155 */       count++;
/* 156 */       line.setLine(xx, y1, xx, y2);
/* 157 */       g2.setPaint(this.palette.getColor(count));
/* 158 */       g2.draw(line);
/* 159 */       xx += 1.0D;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPalette(ColorPalette palette)
/*     */   {
/* 169 */     this.palette = palette;
/* 170 */     repaint();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/editor/PaletteSample.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */