/*     */ package org.processmining.contexts.util;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.plaf.basic.BasicComboBoxRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ToolTipComboBoxRenderer
/*     */   extends BasicComboBoxRenderer
/*     */ {
/*     */   private static final long serialVersionUID = 513853339785774728L;
/*     */   
/*     */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/*     */   {
/* 112 */     if (isSelected) {
/* 113 */       setBackground(list.getSelectionBackground());
/* 114 */       setForeground(list.getSelectionForeground());
/* 115 */       if (-1 < index) {
/* 116 */         list.setToolTipText(value.toString());
/*     */       }
/*     */     } else {
/* 119 */       setBackground(list.getBackground());
/* 120 */       setForeground(list.getForeground());
/*     */     }
/* 122 */     setFont(list.getFont());
/* 123 */     setText(value == null ? "" : value.toString());
/* 124 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/contexts/util/ToolTipComboBoxRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */