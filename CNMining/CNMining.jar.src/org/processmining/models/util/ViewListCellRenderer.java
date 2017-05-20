/*     */ package org.processmining.models.util;
/*     */ 
/*     */ import com.fluxicon.slickerbox.factory.SlickerDecorator;
/*     */ import com.fluxicon.slickerbox.factory.SlickerFactory;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Collection;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ import org.processmining.framework.util.Pair;
/*     */ import org.processmining.models.graphbased.directed.DirectedGraphElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ViewListCellRenderer
/*     */   implements ListCellRenderer
/*     */ {
/*     */   private static final long serialVersionUID = -808355468668630456L;
/* 169 */   private static final CompoundBorder BORDER = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(2, 5, 2, 5));
/*     */   
/* 171 */   private static final CompoundBorder SELBORDER = BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(2, 5, 2, 5));
/*     */   
/*     */ 
/* 174 */   private static final JLabel LABEL = SlickerFactory.instance().createLabel("test");
/* 175 */   private static final Border EMPTYBORDER = BorderFactory.createEmptyBorder(2, 5, 2, 5);
/*     */   private final boolean allowsClick;
/*     */   
/*     */   public ViewListCellRenderer(boolean allowsClick) {
/* 179 */     this.allowsClick = allowsClick;
/* 180 */     LABEL.setFont(new Font("Dialog", 1, 12));
/*     */     
/* 182 */     LABEL.setBorder(EMPTYBORDER);
/* 183 */     LABEL.setOpaque(false);
/* 184 */     SlickerDecorator.instance().decorate(LABEL);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Component getListCellRendererComponent(JList component, Object value, int index, boolean isSelected, boolean cellHasFocus)
/*     */   {
/* 191 */     Pair<Collection<? extends DirectedGraphElement>, String> pair = (Pair)value;
/* 192 */     if (pair == ListSelectionPanel.NONE) {
/* 193 */       LABEL.setText("None");
/* 194 */       LABEL.setForeground(Color.DARK_GRAY);
/* 195 */       LABEL.setBorder(EMPTYBORDER);
/* 196 */       return LABEL;
/*     */     }
/* 198 */     String label = ((String)pair.getSecond()).trim();
/* 199 */     LABEL.setText(fitLabelToWidth(LABEL, label, component.getWidth()));
/* 200 */     LABEL.setToolTipText(label);
/* 201 */     if (this.allowsClick) {
/* 202 */       LABEL.setForeground(isSelected ? Color.WHITE : Color.BLACK);
/* 203 */       LABEL.setBorder(isSelected ? SELBORDER : BORDER);
/*     */     } else {
/* 205 */       LABEL.setBorder(EMPTYBORDER);
/*     */     }
/*     */     
/* 208 */     return LABEL;
/*     */   }
/*     */   
/*     */   public static String fitLabelToWidth(JComponent c, String label, int maxWidth)
/*     */   {
/* 213 */     FontMetrics metrics = c.getFontMetrics(c.getFont());
/* 214 */     Graphics g = c.getGraphics();
/* 215 */     boolean abbreviated = false;
/* 216 */     int width = Integer.MAX_VALUE;
/*     */     
/* 218 */     while (label.length() >= 2)
/*     */     {
/*     */ 
/* 221 */       String test = label;
/* 222 */       if (abbreviated) {
/* 223 */         test = test + "...";
/*     */       }
/* 225 */       Rectangle2D stringBounds = metrics.getStringBounds(test, g);
/* 226 */       width = (int)stringBounds.getWidth();
/* 227 */       if (width <= maxWidth) break;
/* 228 */       label = label.substring(0, label.length() - 1);
/* 229 */       if (!abbreviated) {
/* 230 */         abbreviated = true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 236 */     if (abbreviated) {
/* 237 */       label = label + "...";
/*     */     }
/* 239 */     return label;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/models/util/ViewListCellRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */